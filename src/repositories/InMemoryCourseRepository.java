package repositories;

import entities.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryCourseRepository implements CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public List<Course> listAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Optional<Course> findOne(Long id) {
        return courses.stream()
                .filter(course -> Objects.equals(course.getId(), id))
                .findFirst();
    }

    @Override
    public boolean update(Long id, Course updatedCourse) {
        for (int index = 0; index < courses.size(); index++) {
            if (Objects.equals(courses.get(index).getId(), id)) {
                updatedCourse.setId(id);
                courses.set(index, updatedCourse);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return courses.removeIf(course -> Objects.equals(course.getId(), id));
    }

    @Override
    public Long generateId() {
        return nextId++;
    }
}
