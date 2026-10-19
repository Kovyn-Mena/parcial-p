import controllers.CourseController;
import presentation.ConsoleCourseView;
import repositories.CourseRepository;
import repositories.FileCourseRepository;
import repositories.InMemoryCourseRepository;
import services.CourseService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // In-memory persistence (default)
        CourseRepository courseRepository = new InMemoryCourseRepository();

        // File persistence: comment the line above and uncomment this one
        // CourseRepository courseRepository = new FileCourseRepository("courses.dat");

        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService);

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleCourseView courseView = new ConsoleCourseView(scanner, courseController);
            courseView.run();
        }
    }
}
