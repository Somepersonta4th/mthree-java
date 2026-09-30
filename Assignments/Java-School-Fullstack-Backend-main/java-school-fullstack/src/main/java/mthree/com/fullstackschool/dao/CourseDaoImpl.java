package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.CourseMapper;
import mthree.com.fullstackschool.model.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        String query = """
INSERT INTO course(cid,courseCode,courseDesc,teacherId)
VALUES (?,?,?,?);""";

        jdbcTemplate.update(query,
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId());

        return course;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        String query = """
SELECT cid, courseCode, courseDesc, teacherId 
FROM course;""";
        return jdbcTemplate.query(query, new CourseMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE

        String query = """
SELECT cid, courseCode, courseDesc, teacherId 
FROM course
WHERE cid = ?;""";

        return jdbcTemplate.queryForObject(query, new CourseMapper(),id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE

        String query = """
UPDATE course
SET
    courseCode = ?,
    courseDesc = ?,
    teacherId = ?
WHERE cid = ?""";

        jdbcTemplate.update(query,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId(),
                course.getCourseId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE

        String query = """
DELETE FROM course WHERE cid = ?;""";

        jdbcTemplate.update(query,id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE

        String query = """
DELETE FROM course_student WHERE course_id = ?;""";

        jdbcTemplate.update(query,courseId);

        //YOUR CODE ENDS HERE
    }
}
