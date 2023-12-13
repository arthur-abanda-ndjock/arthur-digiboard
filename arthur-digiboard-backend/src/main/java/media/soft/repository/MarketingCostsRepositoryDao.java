package media.soft.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import media.soft.model.CategoryCost;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;

@Repository
public class MarketingCostsRepositoryDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarketingCostsRepositoryDao.class);

	private final NamedParameterJdbcTemplate namedJdbcTemplate;

	public MarketingCostsRepositoryDao(@Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void insert(MarketingCost cost) {
		String sql = "INSERT INTO MarketingCosts (subcategory_id, cost, date_recorded) "
				+
				"VALUES (:subcategory_id, :cost, :date_recorded)";

		Map<String, Object> params = new HashMap<>();
		params.put("subcategory_id", cost.getSubcategoryId());
		params.put("cost", cost.getCost());
		params.put("date_recorded", cost.getDateRecorded());

		namedJdbcTemplate.update(sql, params);
	}

	public void updateById(int id, MarketingCost cost) {
		String sql = "INSERT INTO MarketingCosts (subcategory_id, cost, date_recorded) "
				+
				"VALUES (:subcategory_id, :cost, :date_recorded) WHERE id = :id";

		Map<String, Object> params = new HashMap<>();

		params.put("id", id);
		params.put("subcategory_id", cost.getSubcategoryId());
		params.put("cost", cost.getCost());
		params.put("date_recorded", cost.getDateRecorded());

		namedJdbcTemplate.update(sql, params);
	}

	public void deleteById(int id) {
		String sql = "DELETE FROM MarketingCosts WHERE id = :id";

		Map<String, Object> params = new HashMap<>();
		params.put("id", id);

		namedJdbcTemplate.update(sql, params);
	}

	// get all cost
	public List<MarketingCost> getAll() {
		String sql = "SELECT * FROM MarketingCosts";
		return namedJdbcTemplate.query(sql, new MarketingCostsRowMapper());
	}

	// get total cost sum
	public BigDecimal getMarketingCostSum() {
		String sql = "SELECT sum(cost) FROM MarketingCosts";
		return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), BigDecimal.class);
	}

	// Recents
	public List<MarketingCostRecap> getRecentCost() {
		String sql = "SELECT costs.id, cat.category_name, subcat.subcategory_name, costs.cost, costs.date_recorded " +
				"from DigitalMarketingCategories cat " +
				"join DigitalMarketingSubcategories subcat on cat.id = subcat.category_id  " +
				"join MarketingCosts costs on costs.subcategory_id = subcat.id " +
				"ORDER BY costs.date_recorded DESC limit 10;";
		return namedJdbcTemplate.query(sql, new MarketingCostRecapRowMapper());
	}

	// get total amounts for each category using a RowMapper
	public List<CategoryCost> getTotalAmountByCategory() {
		String sql = "SELECT c.category_name as name, SUM(mc.cost) as totalCost " +
				"FROM DigitalMarketingCategories c " +
				"JOIN DigitalMarketingSubcategories sc ON c.id = sc.category_id " +
				"JOIN MarketingCosts mc ON sc.id = mc.subcategory_id " +
				"GROUP BY c.id, c.category_name " +
				"ORDER BY c.id";
		return namedJdbcTemplate.query(sql, new CategoryCostRowMapper());
	}

	// get total amounts for each subcategory using a RowMapper
	public List<CategoryCost> getTotalAmountBySubcategory() {
		String sql = "SELECT sc.subcategory_name as name, SUM(mc.cost) as totalCost " +
				"FROM DigitalMarketingSubcategories sc " +
				"JOIN MarketingCosts mc ON sc.id = mc.subcategory_id " +
				"GROUP BY sc.id, sc.subcategory_name " +
				"ORDER BY sc.id";
		return namedJdbcTemplate.query(sql, new CategoryCostRowMapper());
	}

	public List<MarketingCost> getMarketingCostsByPeriod(LocalDate start, LocalDate end) {
		String sql = "SELECT * FROM MarketingCosts where date_recorded between :start and :end ";
		MapSqlParameterSource paramMap = new MapSqlParameterSource()
				.addValue("start", start)
				.addValue("end", end);
		return namedJdbcTemplate.query(sql, paramMap, new MarketingCostsRowMapper());
	}

	public LocalDate getLatestMarketingCostDate() {
		String sql = "SELECT max(date_recorded) from MarketingCosts";
		return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), LocalDate.class);
	}

	private static class CategoryCostRowMapper implements RowMapper<CategoryCost> {
		@Override
		public CategoryCost mapRow(ResultSet rs, int rowNum) throws SQLException {
			CategoryCost categoryCost = new CategoryCost();
			categoryCost.setName(rs.getString("name"));
			categoryCost.setTotalCost(rs.getDouble("totalCost"));
			return categoryCost;
		}
	}

	// RowMapper
	private static class MarketingCostsRowMapper implements RowMapper<MarketingCost> {
		@Override
		public MarketingCost mapRow(ResultSet rs, int rowNum) throws SQLException {

			MarketingCost item = new MarketingCost();

			item.setId(rs.getInt("id"));
			item.setSubcategoryId(rs.getInt("subcategory_id"));
			item.setCost(rs.getDouble("cost"));
			item.setDateRecorded(rs.getDate("date_recorded").toLocalDate());

			return item;
		}
	}

	// RowMapper
	private static class MarketingCostRecapRowMapper implements RowMapper<MarketingCostRecap> {
		@Override
		public MarketingCostRecap mapRow(ResultSet rs, int rowNum) throws SQLException {

			MarketingCostRecap item = new MarketingCostRecap();

			item.setCostId(rs.getInt("id"));
			item.setCategoryName(rs.getString("category_name"));
			item.setSubcategoryName(rs.getString("subcategory_name"));
			item.setCost(rs.getDouble("cost"));
			item.setDateRecorded(rs.getDate("date_recorded").toLocalDate());

			return item;
		}
	}
}
