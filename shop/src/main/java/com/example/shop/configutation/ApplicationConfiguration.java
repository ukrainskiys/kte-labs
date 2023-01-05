package com.example.shop.configutation;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}
}
