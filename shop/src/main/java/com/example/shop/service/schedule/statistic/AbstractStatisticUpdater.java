package com.example.shop.service.schedule.statistic;

import com.example.shop.service.schedule.Updater;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractStatisticUpdater implements Updater {
	final JdbcTemplate jdbcTemplate;

	protected AbstractStatisticUpdater(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	List<StatisticRow> getStatisticRows() {
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

		return jdbcTemplate.query(query, (rs, rowNum) -> resultSetToStatisticRow(rs));
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
