package controllers;

import entities.Course;
import services.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    public Course create(Course course) {
        return service.create(course);
    }

    public List<Course> listAll() {
        return service.listAll();
    }

    public Optional<Course> findOne(Long id) {
        return service.findOne(id);
    }

    public boolean update(Long id, Course course) {
        return service.update(id, course);
    }

    public boolean delete(Long id) {
        return service.delete(id);
    }
}
