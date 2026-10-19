package services;

import entities.Course;
import repositories.CourseRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course create(Course course) {
        validateFields(course);
        validateDuplicateName(course);
        course.setId(repository.generateId());
        return repository.create(course);
    }

    public List<Course> listAll() {
        return repository.listAll();
    }

    public Optional<Course> findOne(Long id) {
        return repository.findOne(id);
    }

    public boolean update(Long id, Course course) {
        if (id == null) {
            return false;
        }
        if (repository.findOne(id).isEmpty()) {
            return false;
        }
        course.setId(id);
        validateFields(course);
        validateDuplicateName(course);
        return repository.update(id, course);
    }

    public boolean delete(Long id) {
        return id != null && repository.delete(id);
    }

    // Business logic validations
    private void validateFields(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("El curso es obligatorio.");
        }
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        }
        if (course.getName().trim().length() < 3) {
            throw new IllegalArgumentException("El nombre del curso debe tener al menos 3 caracteres.");
        }
        if (course.getTeacher() == null || course.getTeacher().trim().isEmpty()) {
            throw new IllegalArgumentException("El docente es obligatorio.");
        }
        if (course.getCredits() <= 0 || course.getCredits() > 20) {
            throw new IllegalArgumentException("Los créditos deben estar entre 1 y 20.");
        }
        if (course.getPrice() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (course.getStatus() == null) {
            throw new IllegalArgumentException("El estado del curso es obligatorio.");
        }
    }

    private void validateDuplicateName(Course course) {
        boolean duplicated = repository.listAll().stream()
                .anyMatch(other -> other.getName().equalsIgnoreCase(course.getName())
                        && !Objects.equals(other.getId(), course.getId()));
        if (duplicated) {
            throw new IllegalArgumentException("Ya existe un curso con ese nombre.");
        }
    }
}
