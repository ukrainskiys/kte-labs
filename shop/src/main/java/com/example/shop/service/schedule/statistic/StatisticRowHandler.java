package com.example.shop.service.schedule.statistic;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRowHandler {
    void handle(List<StatisticRow> statisticRows, LocalDateTime now);
}
