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

import media.soft.model.PrdOnlineCourse;

@ExtendWith(MockitoExtension.class)
class PrdOnlineCoursesRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdOnlineCoursesRepositoryDao prdOnlineCoursesRepositoryDao;

    @Test
    void testInsert() {
        PrdOnlineCourse prdOnlineCourse = createSamplePrdOnlineCourse();

        prdOnlineCoursesRepositoryDao.insert(prdOnlineCourse);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testUpdateById() {
        int courseId = 1;
        PrdOnlineCourse prdOnlineCourse = createSamplePrdOnlineCourse();

        prdOnlineCoursesRepositoryDao.updateById(courseId, prdOnlineCourse);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testDeleteById() {
        int courseId = 1;

        prdOnlineCoursesRepositoryDao.deleteById(courseId);

        verify(namedJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testReadAll() {
        when(namedJdbcTemplate.query(anyString(), ArgumentMatchers.<RowMapper<PrdOnlineCourse>>any()))
                .thenReturn(Collections.singletonList(createSamplePrdOnlineCourse()));

        List<PrdOnlineCourse> actualPrdOnlineCourses = prdOnlineCoursesRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(), ArgumentMatchers.<RowMapper<PrdOnlineCourse>>any());

        List<PrdOnlineCourse> expectedPrdOnlineCourses = Collections.singletonList(createSamplePrdOnlineCourse());
        assertEquals(expectedPrdOnlineCourses, actualPrdOnlineCourses);
    }

    private PrdOnlineCourse createSamplePrdOnlineCourse() {
        PrdOnlineCourse prdOnlineCourse = new PrdOnlineCourse();
        prdOnlineCourse.setCourseID(1);
        prdOnlineCourse.setTitle("Sample Title");
        prdOnlineCourse.setInstructor("Sample Instructor");
        prdOnlineCourse.setDuration(10);
        prdOnlineCourse.setDescription("Sample Description");
        prdOnlineCourse.setPrice(99.99);
        return prdOnlineCourse;
    }
}
