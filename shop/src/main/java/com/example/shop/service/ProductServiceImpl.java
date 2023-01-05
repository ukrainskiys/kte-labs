package com.example.shop.service;

import com.example.shop.domain.ProductCountPair;
import com.example.shop.domain.RatingCountPair;
import com.example.shop.domain.dto.ProductDTO;
import com.example.shop.domain.model.Client;
import com.example.shop.domain.model.Product;
import com.example.shop.domain.model.Rating;
import com.example.shop.repository.ProductRepository;
import com.example.shop.api.dto.response.CalculateFinalPriceResponse;
import com.example.shop.api.dto.response.ProductInformationResponse;
import com.example.shop.service.calculate.CalculationResult;
import com.example.shop.service.calculate.Calculator;
import com.example.shop.service.errors.ProductNotFoundException;
import com.example.shop.util.MoneyUtils;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final RatingService ratingService;
	private final ClientService clientService;
	private final SaleFactService saleFactService;
	private final Calculator calculator;
	private final DozerBeanMapper mapper;

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(product -> mapper.map(product, ProductDTO.class)).toList();
	}

	@Override
	public ProductInformationResponse getProductInfo(long clientId, long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
		List<Rating> ratings = ratingService.getAllByProductId(productId);

		List<RatingCountPair> ratingPairs = ratings.stream()
			.collect(Collectors.groupingBy(Rating::getRating, Collectors.counting()))
			.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.map(entry -> new RatingCountPair(entry.getKey(), entry.getValue()))
			.toList();

		BigDecimal averageRating = BigDecimal.valueOf(
				ratings.stream()
						.map(Rating::getRating)
						.flatMapToInt(IntStream::of)
						.average()
						.orElse(0)
		).setScale(1, RoundingMode.HALF_UP);

		Integer currentRating = ratingService.getRatingByClientIdAndProductId(clientId, productId);

		return new ProductInformationResponse(product.getName(), averageRating, ratingPairs, currentRating);
	}

	@Override
	public CalculateFinalPriceResponse calculateFinalPrice(long clientId, List<ProductCountPair> sales) {
		Client client = clientService.getClientById(clientId);
		List<CalculationResult> calculates = calculator.calculate(client, sales);
		saleFactService.keepFinalCost(client, sales, calculates);
		long sum = calculates.stream().map(CalculationResult::getSumWithDiscount).mapToLong(MoneyUtils::toKopecks).sum();
		return new CalculateFinalPriceResponse(sum);
	}

	@Override
	public void productEvaluation(long clientId, long productId, Integer rating) {
		ratingService.productEvaluation(clientId, productId, rating);
	}
}
