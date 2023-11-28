package media.soft.service;

import media.soft.model.PrdOnlineCourse;
import media.soft.repository.PrdOnlineCoursesRepositoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrdOnlineCoursesService {

    private final PrdOnlineCoursesRepositoryDao prdOnlineCoursesRepositoryDao;

    public PrdOnlineCoursesService(@Autowired PrdOnlineCoursesRepositoryDao prdOnlineCoursesRepositoryDao){
        this.prdOnlineCoursesRepositoryDao = prdOnlineCoursesRepositoryDao;
    }
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

