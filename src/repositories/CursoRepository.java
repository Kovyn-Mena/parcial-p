package repositories;

import entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {

    Curso create(Curso curso);

    List<Curso> listAll();

    Optional<Curso> findOne(Long id);

    boolean update(Long id, Curso curso);

    boolean delete(Long id);

    Long generateId();
}
