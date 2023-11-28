package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdOnlineCourse;
import media.soft.repository.PrdOnlineCoursesRepositoryDao;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdOnlineCoursesService {

    private final PrdOnlineCoursesRepositoryDao prdOnlineCoursesRepositoryDao;

    public void insertPrdOnlineCourses(PrdOnlineCourse prdOnlineCourse) {
        prdOnlineCoursesRepositoryDao.insert(prdOnlineCourse);
    }

    public void updatePrdOnlineCoursesById(int courseId, PrdOnlineCourse prdOnlineCourse) {
        prdOnlineCoursesRepositoryDao.updateById(courseId, prdOnlineCourse);
    }

    public void deletePrdOnlineCoursesById(int courseId) {
        prdOnlineCoursesRepositoryDao.deleteById(courseId);
    }
}

