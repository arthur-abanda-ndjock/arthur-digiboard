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

import media.soft.model.PrdStreamingService;

@ExtendWith(MockitoExtension.class)
class PrdStreamingServicesRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdStreamingServicesRepositoryDao prdStreamingServicesRepositoryDao;

    @Test
    void testInsert() {
        PrdStreamingService prdStreamingService = createSamplePrdStreamingService();

        prdStreamingServicesRepositoryDao.insert(prdStreamingService);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testUpdateById() {
        int serviceId = 1;
        PrdStreamingService prdStreamingService = createSamplePrdStreamingService();

        prdStreamingServicesRepositoryDao.updateById(serviceId, prdStreamingService);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testDeleteById() {
        int serviceId = 1;

        prdStreamingServicesRepositoryDao.deleteById(serviceId);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testReadAll() {
        when(namedJdbcTemplate.query(anyString(),
                ArgumentMatchers.<RowMapper<PrdStreamingService>>any()))
                .thenReturn(Collections.singletonList(createSamplePrdStreamingService()));

        List<PrdStreamingService> actualPrdStreamingServices = prdStreamingServicesRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(),
                ArgumentMatchers.<RowMapper<PrdStreamingService>>any());

        List<PrdStreamingService> expectedPrdStreamingServices = Collections
                .singletonList(createSamplePrdStreamingService());
        assertEquals(expectedPrdStreamingServices, actualPrdStreamingServices);
    }

    private PrdStreamingService createSamplePrdStreamingService() {
        PrdStreamingService prdStreamingService = new PrdStreamingService();
        prdStreamingService.setServiceID(1);
        prdStreamingService.setName("Sample Name");
        prdStreamingService.setPlatform("Sample Platform");
        prdStreamingService.setSubscriptionCost(9.99);
        prdStreamingService.setContentLibrarySize(1000);
        return prdStreamingService;
    }
}
