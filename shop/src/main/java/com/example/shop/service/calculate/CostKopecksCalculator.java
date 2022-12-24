package com.example.shop.service.calculate;

import com.example.shop.model.SalePair;
import com.example.shop.model.entity.Client;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.ProductDiscount;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.errors.ProductNotFoundException;
import com.example.shop.service.schedule.NewDiscountingProductEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CostKopecksCalculator implements KopecksCalculator {
	private final JdbcTemplate jdbcTemplate;
	private final ProductRepository productRepository;
	private long currentDiscountingProductId;
	private long currentDiscountingProductValue;

	@PostConstruct
	public void init() {
		jdbcTemplate.query("select * from product_discount limit 1", (RowMapper<ProductDiscount>) (rs, rowNum) -> {
			currentDiscountingProductId = rs.getLong("id");
			currentDiscountingProductValue = rs.getLong("product_discount");
			return null;
		});
	}

	@EventListener
	public void onNewDiscountingProductEvent(NewDiscountingProductEvent event) {
		currentDiscountingProductId = event.getProductId();
		currentDiscountingProductValue = event.getDiscount();
	}

	@Override
	public List<CalculationResult> calculate(Client client, List<SalePair> sales) {
		List<CalculationResult> calculation = new ArrayList<>();
		for (SalePair pair : sales) {
			int clientDiscount = client.getIndividualDiscountFirst();
			if (pair.getCount() >= 5 && client.getIndividualDiscountSecond() != 0) {
				clientDiscount = client.getIndividualDiscountSecond();
			}
			calculation.add(calculateWithClientDiscount(clientDiscount, pair));
		}
		return calculation;
	}

	private CalculationResult calculateWithClientDiscount(int clientDiscount, SalePair pair) {
		long id = pair.getProductId();
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		int finalDiscount = clientDiscount;
		if (product.getId() == currentDiscountingProductId) {
			finalDiscount += currentDiscountingProductValue;
		}
		finalDiscount = Math.min(finalDiscount, 18);

		BigDecimal sum = product.getPrice().multiply(BigDecimal.valueOf(pair.getCount()));
		BigDecimal sumWithDiscount = sum.multiply(
			BigDecimal.valueOf(100 - finalDiscount).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
		);
		return new CalculationResult(finalDiscount, toKopecks(sumWithDiscount), toKopecks(sum.subtract(sumWithDiscount)));
	}

	private long toKopecks(BigDecimal decimal) {
		return decimal.multiply(BigDecimal.valueOf(100)).longValue();
	}
}
