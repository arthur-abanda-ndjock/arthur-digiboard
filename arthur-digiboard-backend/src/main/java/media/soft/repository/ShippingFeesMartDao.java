package media.soft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import media.soft.model.ShippingFeesMart;

@Repository
public class ShippingFeesMartDao {

	private final JdbcTemplate jdbcTemplate;

	public ShippingFeesMartDao(@Autowired JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertData(int id, String name) {
		String sql = "INSERT INTO ShippingFeesMart (id,name) VALUES (?,?)";
		jdbcTemplate.update(sql, id, name);
	}

	public List<ShippingFeesMart> getAllData() {
		String sql = "SELECT * FROM ShippingFeesMart";
		return jdbcTemplate.query(sql, new ShippingFeesMartRowMapper());
	}

	private static class ShippingFeesMartRowMapper implements RowMapper<ShippingFeesMart> {
		@Override
		public ShippingFeesMart mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// Integer id = resultSet.getInt("id");
			// BigDecimal totalDailyFees = resultSet.getBigDecimal("totalDailyFees");
			// LocalDateTime asofdate =
			// resultSet.getTimestamp("asofdate").toLocalDateTime();

			return new ShippingFeesMart(resultSet.getInt("id"),
					resultSet.getBigDecimal("totalDailyFees"),
					resultSet.getTimestamp("asofdate").toLocalDateTime());
		}
	}
}
