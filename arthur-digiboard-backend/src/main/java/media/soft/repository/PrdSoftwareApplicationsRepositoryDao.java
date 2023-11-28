package media.soft.repository;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdSoftwareApplication;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PrdSoftwareApplicationsRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public void insert(PrdSoftwareApplication prdSoftwareApplication) {
        String sql = "INSERT INTO PrdSoftwareApplications (SoftwareID, Name, Version, Developer, LicenseType, Price, ReleaseDate, Platform) " +
                     "VALUES (:softwareId, :name, :version, :developer, :licenseType, :price, :releaseDate, :platform)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("softwareId", prdSoftwareApplication.getSoftwareID());
        params.put("name", prdSoftwareApplication.getName());
        params.put("version", prdSoftwareApplication.getVersion());
        params.put("developer", prdSoftwareApplication.getDeveloper());
        params.put("licenseType", prdSoftwareApplication.getLicenseType());
        params.put("price", prdSoftwareApplication.getPrice());
        params.put("releaseDate", prdSoftwareApplication.getReleaseDate());
        params.put("platform", prdSoftwareApplication.getPlatform());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int softwareId, PrdSoftwareApplication prdSoftwareApplication) {
        String sql = "UPDATE PrdSoftwareApplications " +
                     "SET Name = :name, Version = :version, Developer = :developer, " +
                     "LicenseType = :licenseType, Price = :price, ReleaseDate = :releaseDate, Platform = :platform " +
                     "WHERE SoftwareID = :softwareId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("softwareId", softwareId);
        params.put("name", prdSoftwareApplication.getName());
        params.put("version", prdSoftwareApplication.getVersion());
        params.put("developer", prdSoftwareApplication.getDeveloper());
        params.put("licenseType", prdSoftwareApplication.getLicenseType());
        params.put("price", prdSoftwareApplication.getPrice());
        params.put("releaseDate", prdSoftwareApplication.getReleaseDate());
        params.put("platform", prdSoftwareApplication.getPlatform());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int softwareId) {
        String sql = "DELETE FROM PrdSoftwareApplications WHERE SoftwareID = :softwareId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("softwareId", softwareId);

        namedJdbcTemplate.update(sql, params);
    }
}
