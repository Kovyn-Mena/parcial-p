package repositories;

import entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    Course create(Course course);

    List<Course> listAll();

    Optional<Course> findOne(Long id);

    boolean update(Long id, Course course);

    boolean delete(Long id);

    Long generateId();
}
