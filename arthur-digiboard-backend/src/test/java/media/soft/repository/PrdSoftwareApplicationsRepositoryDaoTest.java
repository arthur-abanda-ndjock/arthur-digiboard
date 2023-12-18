package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import media.soft.model.PrdSoftwareApplication;

@ExtendWith(MockitoExtension.class)
class PrdSoftwareApplicationsRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdSoftwareApplicationsRepositoryDao prdSoftwareApplicationsRepositoryDao;

    @Test
    void testInsert() {
        PrdSoftwareApplication prdSoftwareApplication = createSamplePrdSoftwareApplication();

        prdSoftwareApplicationsRepositoryDao.insert(prdSoftwareApplication);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testUpdateById() {
        int softwareId = 1;
        PrdSoftwareApplication prdSoftwareApplication = createSamplePrdSoftwareApplication();

        prdSoftwareApplicationsRepositoryDao.updateById(softwareId, prdSoftwareApplication);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testDeleteById() {
        int softwareId = 1;

        prdSoftwareApplicationsRepositoryDao.deleteById(softwareId);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testReadAll() {
        when(namedJdbcTemplate.query(anyString(),
                ArgumentMatchers.<RowMapper<PrdSoftwareApplication>>any()))
                .thenReturn(Collections.singletonList(createSamplePrdSoftwareApplication()));

        List<PrdSoftwareApplication> actualPrdSoftwareApplications = prdSoftwareApplicationsRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(),
                ArgumentMatchers.<RowMapper<PrdSoftwareApplication>>any());

        List<PrdSoftwareApplication> expectedPrdSoftwareApplications = Collections
                .singletonList(createSamplePrdSoftwareApplication());
        assertEquals(expectedPrdSoftwareApplications, actualPrdSoftwareApplications);
    }

    private PrdSoftwareApplication createSamplePrdSoftwareApplication() {
        PrdSoftwareApplication prdSoftwareApplication = new PrdSoftwareApplication();
        prdSoftwareApplication.setSoftwareID(1);
        prdSoftwareApplication.setName("Sample Name");
        prdSoftwareApplication.setVersion("1.0");
        prdSoftwareApplication.setDeveloper("Sample Developer");
        prdSoftwareApplication.setLicenseType("Open Source");
        prdSoftwareApplication.setPrice(99.99);
        prdSoftwareApplication.setReleaseDate(java.sql.Date.valueOf("2022-01-01"));
        prdSoftwareApplication.setPlatform("Windows");
        return prdSoftwareApplication;
    }
}
