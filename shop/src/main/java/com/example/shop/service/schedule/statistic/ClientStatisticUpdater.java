package com.example.shop.service.schedule.statistic;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientStatisticUpdater extends AbstractStatisticUpdater {
    private final JdbcTemplate jdbcTemplate;

    public ClientStatisticUpdater(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void refresh() {
        List<StatisticRow> statisticRows = getStatisticRows();
    }
}
