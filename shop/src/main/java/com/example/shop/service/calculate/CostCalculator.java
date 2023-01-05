package com.example.shop.service.calculate;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.model.Client;
import com.example.shop.domain.model.Product;
import com.example.shop.domain.model.ProductDiscount;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.errors.ProductNotFoundException;
import com.example.shop.service.schedule.NewDiscountingProductEvent;
import com.example.shop.util.IntUtils;
import com.example.shop.util.MoneyUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CostCalculator implements Calculator {
	private ProductDiscount currentDiscounting;
	private final JdbcTemplate jdbcTemplate;
	private final ProductRepository productRepository;

	@PostConstruct
	public void init() {
		jdbcTemplate.query("select * from product_discount limit 1", (RowMapper<ProductDiscount>) (rs, rowNum) -> {
			currentDiscounting = new ProductDiscount(rs.getLong(1), rs.getInt(2));
			return null;
		});
	}

	@EventListener
	public void onNewDiscountingProductEvent(NewDiscountingProductEvent event) {
		currentDiscounting = event.getProductDiscount();
	}

	@Override
	public List<CalculationResult> calculate(Client client, List<ProductCountPair> sales) {
		List<CalculationResult> calculation = new ArrayList<>();
		for (ProductCountPair pair : sales) {
			int clientDiscount = IntUtils.intOrZero(client.getIndividualDiscountFirst());
			int discountSecond = IntUtils.intOrZero(client.getIndividualDiscountSecond());
			if (pair.getCount() >= 5 && discountSecond != 0) {
				clientDiscount = discountSecond;
			}
			calculation.add(calculate(clientDiscount, pair));
		}
		return calculation;
	}

	private CalculationResult calculate(int clientDiscount, ProductCountPair pair) {
		long id = pair.getProductId();
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		if (Objects.equals(product.getId(), currentDiscounting.getProductId())) {
			clientDiscount += currentDiscounting.getPercentDiscount();
		}
		clientDiscount = Math.min(clientDiscount, 18);

		BigDecimal sum = product.getPrice().multiply(BigDecimal.valueOf(pair.getCount()));
		BigDecimal sumWithDiscount = MoneyUtils.ofPercent(sum, 100 - clientDiscount);
		return new CalculationResult(clientDiscount, sum, sumWithDiscount);
	}
}
