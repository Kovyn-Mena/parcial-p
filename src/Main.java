import controllers.CursoController;
import presentation.ConsoleCursoView;
import repositories.CursoRepository;
import repositories.FileCursoRepository;
import repositories.InMemoryCursoRepository;
import services.CursoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Persistencia en memoria (por defecto)
        CursoRepository repository = new InMemoryCursoRepository();

        // Persistencia en archivo: comente la línea anterior y descomente esta
        // CursoRepository repository = new FileCursoRepository("cursos.dat");

        CursoService service = new CursoService(repository);
        CursoController controller = new CursoController(service);

        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleCursoView view = new ConsoleCursoView(scanner, controller);
            view.run();
        }
    }
}
