package com.example.shop.service.schedule.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticAggregatorImpl implements StatisticAggregator {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<StatisticRow> getStatisticRows() {
        LocalDateTime lastUpdateDate = getLastUpdateDate();
        String query = """
			SELECT
			sf.client_id AS client_id,
			sfp.product_id AS product_id,
			sf.sale_date AS "date",
			sf.check_number AS "check",
			sfp.final_price AS price,
			sfp.final_discount_percent AS discount
			FROM sale_fact_positions sfp
			LEFT JOIN sale_facts sf ON sf.id = sfp.sale_fact_id
			WHERE sf.check_number IS NOT NULL
			AND sf.sale_date > ?
			""";

        return jdbcTemplate.query(query, (rs, rowNum) -> resultSetToStatisticRow(rs), lastUpdateDate);
    }

    private LocalDateTime getLastUpdateDate() {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT last_update FROM statistics ORDER BY last_update DESC LIMIT 1", LocalDateTime.class);
        } catch (DataAccessException e) {
            return LocalDateTime.MIN;
        }
    }

    private StatisticRow resultSetToStatisticRow(ResultSet rs) throws SQLException {
        StatisticRow statisticRow = new StatisticRow();
        statisticRow.setClientId(rs.getLong("client_id"));
        statisticRow.setProductId(rs.getLong("product_id"));
        statisticRow.setDate(rs.getTimestamp("date").toLocalDateTime());
        statisticRow.setCheck(rs.getString("check"));
        statisticRow.setFinalPrice(rs.getBigDecimal("price"));
        statisticRow.setFinalDiscount(rs.getInt("discount"));
        return statisticRow;
    }
}
