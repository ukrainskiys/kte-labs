package com.example.shop.service.schedule;

import com.example.shop.model.ProductDiscount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Ивент создается каждый раз при изменении дисконтного товара
 * в {@link ProductDiscountUpdater#refresh()}
 */
@Getter
public class NewDiscountingProductEvent extends ApplicationEvent {
	private final ProductDiscount productDiscount;

	public NewDiscountingProductEvent(Object source, long productId, int discount) {
		super(source);
		this.productDiscount = new ProductDiscount(productId, discount);
	}
}
