package repositories;

import entities.Course;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileCourseRepository implements CourseRepository {

    private final String fileName;
    private List<Course> courses = new ArrayList<>();

    public FileCourseRepository(String fileName) {
        this.fileName = fileName;
        loadFromFile();
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            courses = (List<Course>) input.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No se pudo leer el archivo " + fileName + ": " + exception.getMessage());
            courses = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(courses);
        } catch (IOException exception) {
            System.out.println("No se pudo guardar el archivo " + fileName + ": " + exception.getMessage());
        }
    }

    @Override
    public Course create(Course course) {
        courses.add(course);
        saveToFile();
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
                saveToFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        boolean deleted = courses.removeIf(course -> Objects.equals(course.getId(), id));
        if (deleted) {
            saveToFile();
        }
        return deleted;
    }

    @Override
    public Long generateId() {
        long maxId = 0;
        for (Course course : courses) {
            if (course.getId() != null && course.getId() > maxId) {
                maxId = course.getId();
            }
        }
        return maxId + 1;
    }
}
