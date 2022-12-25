package com.example.shop.service.schedule.statistic;

import com.example.shop.service.schedule.Updater;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientStatisticUpdater implements Updater {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void refresh() {
        String query = """
                SELECT
                sf.client_id as id,
                sf.sale_date as "date",
                sf.check_number as "check",
                sfp.final_price as final_price,
                sfp.final_discount_percent as final_percent
                FROM sale_fact_positions sfp
                LEFT JOIN sale_facts sf ON sf.id = sfp.sale_fact_id
                """;
        List<ClientStatistic> clientStatistics = jdbcTemplate.query(query, (rs, rowNum) -> new ClientStatistic(
                rs.getLong(1),
                rs.getTimestamp(2).toLocalDateTime(),
                rs.getString(3),
                rs.getBigDecimal(4),
                rs.getInt(5)
        ));


    }

    @Getter
    @AllArgsConstructor
    static class ClientStatistic {
        private final long id;
        private final LocalDateTime date;
        private final String check;
        private final BigDecimal finalPrice;
        private final int finalDiscount;
    }
}
