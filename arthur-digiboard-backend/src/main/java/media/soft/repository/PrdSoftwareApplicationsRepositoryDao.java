package media.soft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import media.soft.model.PrdSoftwareApplication;

@Repository
public class PrdSoftwareApplicationsRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdSoftwareApplicationsRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void insert(PrdSoftwareApplication prdSoftwareApplication) {
        String sql = "INSERT INTO PrdSoftwareApplications (SoftwareID, Name, Version, Developer, LicenseType, Price, ReleaseDate, Platform) "
                +
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

    public List<PrdSoftwareApplication> readAll() {
        String sql = "SELECT * FROM PrdSoftwareApplications";
        return namedJdbcTemplate.query(sql, new PrdSoftwareApplicationRowMapper());
    }

    private static class PrdSoftwareApplicationRowMapper implements RowMapper<PrdSoftwareApplication> {
        @Override
        public PrdSoftwareApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdSoftwareApplication prdSoftwareApplication = new PrdSoftwareApplication();
            prdSoftwareApplication.setSoftwareID(rs.getInt("SoftwareID"));
            prdSoftwareApplication.setName(rs.getString("Name"));
            prdSoftwareApplication.setVersion(rs.getString("Version"));
            prdSoftwareApplication.setDeveloper(rs.getString("Developer"));
            prdSoftwareApplication.setLicenseType(rs.getString("LicenseType"));
            prdSoftwareApplication.setPrice(rs.getDouble("Price"));
            prdSoftwareApplication.setReleaseDate(rs.getDate("ReleaseDate"));
            prdSoftwareApplication.setPlatform(rs.getString("Platform"));
            return prdSoftwareApplication;
        }
    }
}
