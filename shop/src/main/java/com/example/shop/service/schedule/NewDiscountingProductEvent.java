package com.example.shop.service.schedule;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Ивент создается каждый раз при изменении дисконтного товара
 * в {@link ProductDiscountUpdater#refresh()}
 */
@Getter
public class NewDiscountingProductEvent extends ApplicationEvent {
	private final long productId;
	private final int discount;

	public NewDiscountingProductEvent(Object source, long productId, int discount) {
		super(source);
		this.productId = productId;
		this.discount = discount;
	}
}
