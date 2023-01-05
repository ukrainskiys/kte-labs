package com.example.shop.service.schedule;

import com.example.shop.repository.SaleFactRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CheckGeneratorImpl implements CheckGenerator, Updater {
	private static final int START_CHECK_VALUE = 100;

	private final SaleFactRepository saleFactRepository;
	private int currentCheck = START_CHECK_VALUE;

	@PostConstruct
	public void init() {
		saleFactRepository.findLastCheckToday(LocalDateTime.now().withHour(0))
			.ifPresent(s -> currentCheck = Integer.parseInt(s));
	}

	@Override
	public String nextCheck() {
		currentCheck++;
		if (currentCheck < 1000) {
			return "00" + currentCheck;
		} else if (currentCheck < 10000) {
			return "0" + currentCheck;
		} else {
			return Integer.toString(currentCheck);
		}
	}

	@Override
	@Scheduled(cron = "0 0 0 * * *")
	public void refresh() {
		currentCheck = START_CHECK_VALUE;
	}
}
