package presentation;

import controllers.CursoController;
import entities.Curso;
import entities.EstadoCurso;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleCursoView {

    private final Scanner scanner;
    private final CursoController controller;

    public ConsoleCursoView(Scanner scanner, CursoController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== MENÚ CURSOS =====");
            System.out.println("1. Registrar curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Buscar curso por id");
            System.out.println("4. Actualizar curso");
            System.out.println("5. Eliminar curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    create();
                    break;
                case "2":
                    showAll();
                    break;
                case "3":
                    find();
                    break;
                case "4":
                    update();
                    break;
                case "5":
                    delete();
                    break;
                case "0":
                    running = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void create() {
        try {
            Curso curso = controller.create(readEntity());
            System.out.println("Curso creado con id " + curso.getId());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showAll() {
        List<Curso> cursos = controller.listAll();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        for (Curso curso : cursos) {
            showEntity(curso);
            System.out.println("----------------------------------------");
        }
    }

    private void find() {
        readId().ifPresent(id -> {
            Optional<Curso> curso = controller.findOne(id);
            if (curso.isPresent()) {
                showEntity(curso.get());
            } else {
                System.out.println("No se encontró un curso con ese id.");
            }
        });
    }

    private void update() {
        readId().ifPresent(id -> {
            try {
                boolean updated = controller.update(id, readEntity());
                System.out.println(updated ? "Curso actualizado." : "No se encontró un curso con ese id.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        });
    }

    private void delete() {
        readId().ifPresent(id -> System.out.println(
                controller.delete(id) ? "Curso eliminado." : "No se encontró un curso con ese id."));
    }

    // Lectura de datos por consola
    private Curso readEntity() {
        Curso curso = new Curso();
        curso.setNombre(readValue("Nombre del curso: "));
        curso.setDocente(readValue("Docente: "));
        curso.setCreditos(readInt("Créditos (1 a 20): "));
        curso.setPrecio(readDouble("Precio: "));
        curso.setEstado(readEstado());
        return curso;
    }

    private void showEntity(Curso curso) {
        System.out.println("Id: " + curso.getId());
        System.out.println("Nombre: " + curso.getNombre());
        System.out.println("Docente: " + curso.getDocente());
        System.out.println("Créditos: " + curso.getCreditos());
        System.out.println("Precio: " + curso.getPrecio());
        System.out.println("Estado: " + curso.getEstado());
    }

    private Optional<Long> readId() {
        System.out.print("Id: ");
        try {
            return Optional.of(Long.parseLong(scanner.nextLine().trim()));
        } catch (NumberFormatException exception) {
            System.out.println("El id debe ser numérico.");
            return Optional.empty();
        }
    }

    private String readValue(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Debe ingresar un número entero.");
        }
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Debe ingresar un número decimal.");
        }
    }

    private EstadoCurso readEstado() {
        EstadoCurso[] estados = EstadoCurso.values();
        while (true) {
            System.out.println("Estado del curso:");
            for (int i = 0; i < estados.length; i++) {
                System.out.println((i + 1) + ". " + estados[i]);
            }
            System.out.print("Seleccione una opción: ");
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());
                if (option >= 1 && option <= estados.length) {
                    return estados[option - 1];
                }
                System.out.println("Opción fuera de rango.");
            } catch (NumberFormatException exception) {
                System.out.println("Debe ingresar un número.");
            }
        }
    }
}
