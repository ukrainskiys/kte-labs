package com.example.shop.service;

import com.example.shop.model.Position;
import com.example.shop.model.SalePair;
import com.example.shop.model.entity.Client;
import com.example.shop.model.entity.SaleFact;
import com.example.shop.model.entity.SaleFactPosition;
import com.example.shop.repository.SaleFactRepository;
import com.example.shop.controller.dto.response.SaleRegistrationResponse;
import com.example.shop.service.calculate.CalculationResult;
import com.example.shop.service.errors.IncorrectFinalPriceException;
import com.example.shop.service.schedule.CheckGenerator;
import com.example.shop.util.MoneyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SaleFactServiceImpl implements SaleFactService {
	private final SaleFactRepository saleFactRepository;
	private final CheckGenerator checkGenerator;

	@Override
	public void keepFinalCost(Client client, List<SalePair> sales, List<CalculationResult> calculations) {
		List<SaleFactPosition> positions = new ArrayList<>();
		for (int i = 0; i < calculations.size(); i++) {
			CalculationResult calc = calculations.get(i);
			SalePair pair = sales.get(i);

			SaleFactPosition position = new SaleFactPosition();
			position.setFinalPrice(MoneyUtils.fromKopecks(calc.getSumKopecks()));
			position.setFinalDiscountPercent(calc.getDiscount());
			position.setPrice(MoneyUtils.fromKopecks(calc.getSumKopecks() + calc.getDiscountSumKopecks()));
			position.setCount(pair.getCount());
			position.setProductId(pair.getProductId());

			positions.add(position);
		}

		SaleFact fact = new SaleFact();
		fact.setClient(client);
		fact.setPositions(positions);
		saleFactRepository.save(fact);
	}

	@Override
	public SaleRegistrationResponse saleRegistration(List<SalePair> products, long finalPriceKopecks) {
		List<SaleFact> facts = saleFactRepository.findAllNotProcessedByCountPositions(products.size());
		SaleFact saleFact = facts.stream()
			.filter(history -> isProductsEquals(history.getPositions(), products, finalPriceKopecks))
			.findAny()
			.orElseThrow(() -> new IncorrectFinalPriceException(finalPriceKopecks));

		String check = checkGenerator.nextCheck();
		saleFact.setCheckNumber(check);
		saleFact.setSaleDate(LocalDateTime.now());
		saleFactRepository.save(saleFact);
		return new SaleRegistrationResponse(checkGenerator.nextCheck());
	}

	private boolean isProductsEquals(List<Position> positions, List<SalePair> sales, long sum) {
		Map<Long, Integer> salesMap = new HashMap<>();
		for (SalePair pair : sales) {
			salesMap.merge(pair.getProductId(), pair.getCount(), Integer::sum);
		}
		for (Position position : positions) {
			salesMap.merge(position.getProductId(), position.getCount(), (v1, v2) -> v1 - v2);
		}
		int check = salesMap.values().stream().mapToInt(Integer::valueOf).sum();
		if (check != 0) {
			return false;
		}
		return sum == positions.stream().mapToLong(Position::getFinalPrice).sum();
	}
}
