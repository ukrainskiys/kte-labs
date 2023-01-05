package com.example.shop.service;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.model.Client;
import com.example.shop.domain.model.SaleFact;
import com.example.shop.domain.model.SaleFactPosition;
import com.example.shop.repository.SaleFactPositionRepository;
import com.example.shop.repository.SaleFactRepository;
import com.example.shop.controllers.response.SaleRegistrationResponse;
import com.example.shop.service.calculate.CalculationResult;
import com.example.shop.service.calculate.Calculator;
import com.example.shop.service.errors.IncorrectFinalPriceException;
import com.example.shop.service.schedule.CheckGenerator;
import com.example.shop.util.MoneyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SaleFactServiceImpl implements SaleFactService {
	private final JdbcTemplate jdbcTemplate;
	private final SaleFactRepository saleFactRepository;
	private final SaleFactPositionRepository saleFactPositionRepository;
	private final CheckGenerator checkGenerator;
	private final Calculator calculator;

	@Override
	public void keepFinalCost(Client client, List<ProductCountPair> sales, List<CalculationResult> calculations) {
		SaleFact fact = new SaleFact();
		fact.setClient(client);
		saleFactRepository.saveAndFlush(fact);

		List<SaleFactPosition> positions = new ArrayList<>();
		for (int i = 0; i < calculations.size(); i++) {
			CalculationResult calc = calculations.get(i);
			ProductCountPair pair = sales.get(i);

			SaleFactPosition position = new SaleFactPosition();
			position.setPrice(calc.getSum());
			position.setFinalPrice(calc.getSumWithDiscount());
			position.setFinalDiscountPercent(calc.getDiscount());
			position.setCount(pair.getCount());
			position.setProductId(pair.getProductId());
			position.setSaleFact(fact);
			positions.add(position);
		}
		saleFactPositionRepository.saveAllAndFlush(positions);
	}

	@Override
	public SaleRegistrationResponse saleRegistration(List<ProductCountPair> products, long finalPriceKopecks) {
		String query = """
        	SELECT sf.id AS id, sum(sfp.final_price)
      		FROM sale_fact_positions sfp
      		LEFT JOIN sale_facts sf ON sf.id = sfp.sale_fact_id
      		WHERE sf.check_number IS NULL
      		GROUP BY sf.id
      		HAVING sum(sfp.final_price) = ?
      		""";
		BigDecimal finalPrice = MoneyUtils.fromKopecks(finalPriceKopecks);
		List<Long> listIds = jdbcTemplate.query(query, (rs, rowNum) -> rs.getLong("id"), finalPrice);
		if (listIds.isEmpty()) {
			throw new IncorrectFinalPriceException(finalPriceKopecks);
		}

		List<SaleFact> allById = saleFactRepository.findAllById(listIds);
		for (SaleFact saleFact : allById) {
			List<ProductCountPair> saleFactProductList = saleFact.getPositions().stream()
					.map(pos -> new ProductCountPair(pos.getProductId(), pos.getCount()))
					.toList();

			if (saleFactProductList.equals(products)) {
				long sum = calculator.calculate(saleFact.getClient(), products).stream()
						.map(CalculationResult::getSumWithDiscount)
						.mapToLong(MoneyUtils::toKopecks)
						.sum();

				if (sum == finalPriceKopecks) {
					String check = checkGenerator.nextCheck();
					saleFact.setCheckNumber(check);
					saleFact.setSaleDate(LocalDateTime.now());
					saleFactRepository.save(saleFact);
					return new SaleRegistrationResponse(check);
				}
			}
		}
		throw new IncorrectFinalPriceException(finalPriceKopecks);
	}
}
