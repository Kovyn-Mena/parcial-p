package services;

import entities.Curso;
import repositories.CursoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso create(Curso curso) {
        validateFields(curso);
        validateNombreRepetido(curso);
        curso.setId(repository.generateId());
        return repository.create(curso);
    }

    public List<Curso> listAll() {
        return repository.listAll();
    }

    public Optional<Curso> findOne(Long id) {
        return repository.findOne(id);
    }

    public boolean update(Long id, Curso curso) {
        if (id == null) {
            return false;
        }
        if (repository.findOne(id).isEmpty()) {
            return false;
        }
        curso.setId(id);
        validateFields(curso);
        validateNombreRepetido(curso);
        return repository.update(id, curso);
    }

    public boolean delete(Long id) {
        return id != null && repository.delete(id);
    }

    // Validaciones de la Capa de Lógica
    private void validateFields(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso es obligatorio.");
        }
        if (curso.getNombre() == null || curso.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        }
        if (curso.getNombre().trim().length() < 3) {
            throw new IllegalArgumentException("El nombre del curso debe tener al menos 3 caracteres.");
        }
        if (curso.getDocente() == null || curso.getDocente().trim().isEmpty()) {
            throw new IllegalArgumentException("El docente es obligatorio.");
        }
        if (curso.getCreditos() <= 0 || curso.getCreditos() > 20) {
            throw new IllegalArgumentException("Los créditos deben estar entre 1 y 20.");
        }
        if (curso.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (curso.getEstado() == null) {
            throw new IllegalArgumentException("El estado del curso es obligatorio.");
        }
    }

    private void validateNombreRepetido(Curso curso) {
        boolean repetido = repository.listAll().stream()
                .anyMatch(otro -> otro.getNombre().equalsIgnoreCase(curso.getNombre())
                        && !Objects.equals(otro.getId(), curso.getId()));
        if (repetido) {
            throw new IllegalArgumentException("Ya existe un curso con ese nombre.");
        }
    }
}
