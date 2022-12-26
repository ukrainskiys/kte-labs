package com.example.shop.service.schedule;

import com.example.shop.model.entity.Product;
import com.example.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ProductDiscountUpdater implements Updater {
	private final ApplicationEventPublisher applicationEventPublisher;
	private final JdbcTemplate jdbcTemplate;
	private final ProductRepository productRepository;

	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void refresh() {
		int count = (int) productRepository.count();
		if (count == 0) {
			return;
		}

		Random random = new Random(Clock.systemUTC().millis());
		int numberRow = random.nextInt(count);
		Page<Product> pageProduct = productRepository.findAll(PageRequest.of(numberRow, 1));
		Long productId;
		if (!pageProduct.hasContent()) {
			return;
		}
		productId = pageProduct.get().toList().get(0).getId();
		int discount = 5 + random.nextInt(6);

		updateDiscountingProduct(productId, discount);
		applicationEventPublisher.publishEvent(new NewDiscountingProductEvent(this, productId, discount));
	}

	private void updateDiscountingProduct(long productId, int discount) {
		String query = """
         	START TRANSACTION;
         	TRUNCATE TABLE product_discount;
         	INSERT INTO product_discount VALUES (?,?);
         	COMMIT;
         	""";
		jdbcTemplate.update(query, productId, discount);
	}
}
