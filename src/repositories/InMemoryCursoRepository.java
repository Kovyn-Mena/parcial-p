package repositories;

import entities.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InMemoryCursoRepository implements CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private long nextId = 1;

    @Override
    public Curso create(Curso curso) {
        cursos.add(curso);
        return curso;
    }

    @Override
    public List<Curso> listAll() {
        return new ArrayList<>(cursos);
    }

    @Override
    public Optional<Curso> findOne(Long id) {
        return cursos.stream()
                .filter(curso -> Objects.equals(curso.getId(), id))
                .findFirst();
    }

    @Override
    public boolean update(Long id, Curso cursoActualizado) {
        for (int index = 0; index < cursos.size(); index++) {
            if (Objects.equals(cursos.get(index).getId(), id)) {
                cursoActualizado.setId(id);
                cursos.set(index, cursoActualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return cursos.removeIf(curso -> Objects.equals(curso.getId(), id));
    }

    @Override
    public Long generateId() {
        return nextId++;
    }
}
