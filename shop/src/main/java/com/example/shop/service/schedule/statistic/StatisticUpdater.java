package com.example.shop.service.schedule.statistic;

import com.example.shop.service.schedule.Updater;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class StatisticUpdater implements Updater {
    private final StatisticRowHandler statisticRowHandler;

    @Override
    @Scheduled(fixedDelayString = "${shop.schedule.update-statistics-every-hour}", timeUnit = TimeUnit.HOURS)
    public void refresh() {
        statisticRowHandler.handle(null, LocalDateTime.now());
    }
}
