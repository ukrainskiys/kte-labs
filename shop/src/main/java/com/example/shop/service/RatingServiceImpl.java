package com.example.shop.service;

import com.example.shop.domain.model.Client;
import com.example.shop.domain.model.Product;
import com.example.shop.domain.model.Rating;
import com.example.shop.repository.RatingRepository;
import com.example.shop.service.errors.CustomerNotBoughtThisProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
	private final JdbcTemplate jdbcTemplate;
	private final RatingRepository ratingRepository;

	@Override
	public Integer getRatingByClientIdAndProductId(long clientId, long productId) {
		return ratingRepository.findByClientIdAndProductId(clientId, productId).map(Rating::getRating).orElse(null);
	}

	@Override
	public List<Rating> getAllByProductId(long productId) {
		return ratingRepository.findAllByProductId(productId);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void productEvaluation(long clientId, long productId, Integer rating) {
		if (!isCustomerBoughtThisProduct(clientId, productId)) {
			throw new CustomerNotBoughtThisProduct(clientId, productId);
		}

		if (rating == null) {
			ratingRepository.deleteByClientIdAndProductId(clientId, productId);
		} else {
			Rating r = ratingRepository.findByClientIdAndProductId(clientId, productId).orElse(null);
			if (r == null) {
				r = new Rating();
				Product product = new Product();
				product.setId(productId);
				r.setProduct(product);
				Client client = new Client();
				client.setId(clientId);
				r.setClient(client);
			}
			r.setRating(rating);
			ratingRepository.save(r);
		}
	}

	private boolean isCustomerBoughtThisProduct(long clientId, long productId) {
		String query = """
			SELECT EXISTS(
				SELECT * FROM sale_fact_positions sfp
				LEFT JOIN sale_facts sf ON sf.id = sfp.sale_fact_id
				WHERE sf.client_id = ? AND sfp.product_id = ?
			);
			""";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, Boolean.class, clientId, productId));
	}
}
