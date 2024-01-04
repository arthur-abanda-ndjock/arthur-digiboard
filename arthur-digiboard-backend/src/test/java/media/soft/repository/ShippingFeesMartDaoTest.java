package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import media.soft.model.ShippingFeesMart;

@ExtendWith(MockitoExtension.class)
class ShippingFeesMartDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ShippingFeesMartDao shippingFeesMartDao;

    @Test
    void testInsertData() {
        int id = 1;
        String name = "Sample Name";

        shippingFeesMartDao.insertData(id, name);

        verify(jdbcTemplate).update(anyString(), eq(id), eq(name));
    }

    @Test
    void testGetAllData() {
        when(jdbcTemplate.query(anyString(), ArgumentMatchers.<RowMapper<ShippingFeesMart>>any()))
                .thenReturn(Collections.singletonList(createSampleShippingFeesMart()));

        List<ShippingFeesMart> actualShippingFeesMarts = shippingFeesMartDao.getAllData();

        verify(jdbcTemplate).query(anyString(), ArgumentMatchers.<RowMapper<ShippingFeesMart>>any());

        List<ShippingFeesMart> expectedShippingFeesMarts = Collections.singletonList(createSampleShippingFeesMart());
        assertEquals(expectedShippingFeesMarts, actualShippingFeesMarts);
    }

    private ShippingFeesMart createSampleShippingFeesMart() {
        return new ShippingFeesMart(1, new BigDecimal("100.00"), LocalDateTime.of(2023, 12, 11, 02, 03, 05));
    }
}
