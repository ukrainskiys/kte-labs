package com.example.shop.service;

import com.example.shop.model.ProductDTO;
import com.example.shop.model.RatingPair;
import com.example.shop.model.entity.Client;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.Rating;
import com.example.shop.model.SalePair;
import com.example.shop.repository.ProductRepository;
import com.example.shop.controller.dto.response.CalculateFinalPriceResponse;
import com.example.shop.controller.dto.response.ProductInformationResponse;
import com.example.shop.service.calculate.CalculationResult;
import com.example.shop.service.calculate.KopecksCalculator;
import com.example.shop.service.errors.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
	private final KopecksCalculator kopecksCalculator;

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(ProductDTO::new).toList();
	}

	@Override
	public ProductInformationResponse getProductInfo(long clientId, long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
		List<Rating> ratings = product.getRatings();

		List<RatingPair> ratingPairs = product.getRatings().stream()
			.collect(Collectors.groupingBy(Rating::getRating, Collectors.counting()))
			.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.map(entry -> new RatingPair(entry.getKey(), entry.getValue()))
			.toList();

		BigDecimal averageRating = BigDecimal.valueOf(ratings.stream()
			.map(Rating::getRating)
			.flatMapToInt(IntStream::of)
			.average()
			.orElse(0));

		Integer currentRating = ratingService.getRatingByClientIdAndProductId(clientId, productId);

		return ProductInformationResponse.builder()
			.description(product.getDescription())
			.currentClientRating(currentRating)
			.averageRating(averageRating)
			.ratings(ratingPairs)
			.build();
	}

	@Override
	public CalculateFinalPriceResponse calculateFinalPrice(long clientId, List<SalePair> sales) {
		Client client = clientService.getClientById(clientId);
		List<CalculationResult> calculates = kopecksCalculator.calculate(client, sales);
		saleFactService.keepFinalCost(client, sales, calculates);
		long sum = calculates.stream().mapToLong(CalculationResult::getSumKopecks).sum();
		return CalculateFinalPriceResponse.builder().priceKopecks(sum).build();
	}

	@Override
	public void productEvaluation(long clientId, long productId, Integer rating) {
		ratingService.productEvaluation(clientId, productId, rating);
	}
}
