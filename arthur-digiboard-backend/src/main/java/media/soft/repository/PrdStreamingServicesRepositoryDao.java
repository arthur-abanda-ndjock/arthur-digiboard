package media.soft.repository;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdStreamingService;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PrdStreamingServicesRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

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
}
