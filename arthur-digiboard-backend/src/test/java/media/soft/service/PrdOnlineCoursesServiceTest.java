package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdOnlineCourse;
import media.soft.repository.PrdOnlineCoursesRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdOnlineCoursesServiceTest {

    @Mock
    private PrdOnlineCoursesRepositoryDao prdOnlineCoursesRepositoryDao;

    @InjectMocks
    private PrdOnlineCoursesService prdOnlineCoursesService;

    @Test
    void testInsertPrdOnlineCourses() {
        PrdOnlineCourse prdOnlineCourse = new PrdOnlineCourse();
        prdOnlineCoursesService.insertPrdOnlineCourses(prdOnlineCourse);

        verify(prdOnlineCoursesRepositoryDao).insert(prdOnlineCourse);
    }

    @Test
    void testUpdatePrdOnlineCoursesById() {
        int courseId = 1;
        PrdOnlineCourse prdOnlineCourse = new PrdOnlineCourse();
        prdOnlineCoursesService.updatePrdOnlineCoursesById(courseId, prdOnlineCourse);

        verify(prdOnlineCoursesRepositoryDao).updateById(courseId, prdOnlineCourse);
    }

    @Test
    void testDeletePrdOnlineCoursesById() {
        int courseId = 1;
        prdOnlineCoursesService.deletePrdOnlineCoursesById(courseId);

        verify(prdOnlineCoursesRepositoryDao).deleteById(courseId);
    }
}
