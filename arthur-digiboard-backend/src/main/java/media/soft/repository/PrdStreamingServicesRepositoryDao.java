package media.soft.repository;

import media.soft.model.PrdStreamingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrdStreamingServicesRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdStreamingServicesRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate){
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void insert(PrdStreamingService prdStreamingService) {
        String sql = "INSERT INTO PrdStreamingServices (ServiceID, Name, Platform, SubscriptionCost, ContentLibrarySize) " +
                     "VALUES (:serviceId, :name, :platform, :subscriptionCost, :contentLibrarySize)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("serviceId", prdStreamingService.getServiceID());
        params.put("name", prdStreamingService.getName());
        params.put("platform", prdStreamingService.getPlatform());
        params.put("subscriptionCost", prdStreamingService.getSubscriptionCost());
        params.put("contentLibrarySize", prdStreamingService.getContentLibrarySize());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int serviceId, PrdStreamingService prdStreamingServices) {
        String sql = "UPDATE PrdStreamingServices " +
                     "SET Name = :name, Platform = :platform, SubscriptionCost = :subscriptionCost, " +
                     "ContentLibrarySize = :contentLibrarySize " +
                     "WHERE ServiceID = :serviceId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("serviceId", serviceId);
        params.put("name", prdStreamingServices.getName());
        params.put("platform", prdStreamingServices.getPlatform());
        params.put("subscriptionCost", prdStreamingServices.getSubscriptionCost());
        params.put("contentLibrarySize", prdStreamingServices.getContentLibrarySize());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int serviceId) {
        String sql = "DELETE FROM PrdStreamingServices WHERE ServiceID = :serviceId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("serviceId", serviceId);

        namedJdbcTemplate.update(sql, params);
    }

    public List<PrdStreamingService> readAll() {
        String sql = "SELECT * FROM PrdStreamingServices";
        return namedJdbcTemplate.query(sql, new PrdStreamingServiceRowMapper());
    }

    private static class PrdStreamingServiceRowMapper implements RowMapper<PrdStreamingService> {
        @Override
        public PrdStreamingService mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdStreamingService prdStreamingService = new PrdStreamingService();
            prdStreamingService.setServiceID(rs.getInt("ServiceID"));
            prdStreamingService.setName(rs.getString("Name"));
            prdStreamingService.setPlatform(rs.getString("Platform"));
            prdStreamingService.setSubscriptionCost(rs.getDouble("SubscriptionCost"));
            prdStreamingService.setContentLibrarySize(rs.getInt("ContentLibrarySize"));
            return prdStreamingService;
        }
    }
}
