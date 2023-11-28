package media.soft.repository;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdOnlineCourse;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PrdOnlineCoursesRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public void insert(PrdOnlineCourse prdOnlineCourse) {
        String sql = "INSERT INTO PrdOnlineCourses (CourseID, Title, Instructor, Duration, Description, Price) " +
                     "VALUES (:courseId, :title, :instructor, :duration, :description, :price)";
        
        Map<String, Object> params = new HashMap<>();
        params.put("courseId", prdOnlineCourse.getCourseID());
        params.put("title", prdOnlineCourse.getTitle());
        params.put("instructor", prdOnlineCourse.getInstructor());
        params.put("duration", prdOnlineCourse.getDuration());
        params.put("description", prdOnlineCourse.getDescription());
        params.put("price", prdOnlineCourse.getPrice());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int courseId, PrdOnlineCourse prdOnlineCourses) {
        String sql = "UPDATE PrdOnlineCourses " +
                     "SET Title = :title, Instructor = :instructor, Duration = :duration, " +
                     "Description = :description, Price = :price " +
                     "WHERE CourseID = :courseId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("courseId", courseId);
        params.put("title", prdOnlineCourses.getTitle());
        params.put("instructor", prdOnlineCourses.getInstructor());
        params.put("duration", prdOnlineCourses.getDuration());
        params.put("description", prdOnlineCourses.getDescription());
        params.put("price", prdOnlineCourses.getPrice());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int courseId) {
        String sql = "DELETE FROM PrdOnlineCourses WHERE CourseID = :courseId";
        
        Map<String, Object> params = new HashMap<>();
        params.put("courseId", courseId);

        namedJdbcTemplate.update(sql, params);
    }
}
