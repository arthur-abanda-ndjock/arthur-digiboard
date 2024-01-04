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

import media.soft.model.PrdOnlineCourse;

@Repository
public class PrdOnlineCoursesRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdOnlineCoursesRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

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

    public void updateById(int courseId, PrdOnlineCourse prdOnlineCourse) {
        String sql = "UPDATE PrdOnlineCourses " +
                "SET Title = :title, Instructor = :instructor, Duration = :duration, " +
                "Description = :description, Price = :price " +
                "WHERE CourseID = :courseId";

        Map<String, Object> params = new HashMap<>();
        params.put("courseId", courseId);
        params.put("title", prdOnlineCourse.getTitle());
        params.put("instructor", prdOnlineCourse.getInstructor());
        params.put("duration", prdOnlineCourse.getDuration());
        params.put("description", prdOnlineCourse.getDescription());
        params.put("price", prdOnlineCourse.getPrice());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int courseId) {
        String sql = "DELETE FROM PrdOnlineCourses WHERE CourseID = :courseId";

        Map<String, Object> params = new HashMap<>();
        params.put("courseId", courseId);

        namedJdbcTemplate.update(sql, params);
    }

    public List<PrdOnlineCourse> readAll() {
        String sql = "SELECT * FROM PrdOnlineCourses";
        return namedJdbcTemplate.query(sql, new PrdOnlineCourseRowMapper());
    }

    private static class PrdOnlineCourseRowMapper implements RowMapper<PrdOnlineCourse> {
        @Override
        public PrdOnlineCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdOnlineCourse prdOnlineCourse = new PrdOnlineCourse();
            prdOnlineCourse.setCourseID(rs.getInt("CourseID"));
            prdOnlineCourse.setTitle(rs.getString("Title"));
            prdOnlineCourse.setInstructor(rs.getString("Instructor"));
            prdOnlineCourse.setDuration(rs.getInt("Duration"));
            prdOnlineCourse.setDescription(rs.getString("Description"));
            prdOnlineCourse.setPrice(rs.getDouble("Price"));
            return prdOnlineCourse;
        }
    }
}
