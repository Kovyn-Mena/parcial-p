package repositories;

import entities.Curso;

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

public class FileCursoRepository implements CursoRepository {

    private final String nombreArchivo;
    private List<Curso> cursos = new ArrayList<>();

    public FileCursoRepository(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        cargarArchivo();
    }

    @SuppressWarnings("unchecked")
    private void cargarArchivo() {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return;
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            cursos = (List<Curso>) entrada.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("No se pudo leer el archivo " + nombreArchivo + ": " + exception.getMessage());
            cursos = new ArrayList<>();
        }
    }

    private void guardarArchivo() {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            salida.writeObject(cursos);
        } catch (IOException exception) {
            System.out.println("No se pudo guardar el archivo " + nombreArchivo + ": " + exception.getMessage());
        }
    }

    @Override
    public Curso create(Curso curso) {
        cursos.add(curso);
        guardarArchivo();
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
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        boolean eliminado = cursos.removeIf(curso -> Objects.equals(curso.getId(), id));
        if (eliminado) {
            guardarArchivo();
        }
        return eliminado;
    }

    @Override
    public Long generateId() {
        long maxId = 0;
        for (Curso curso : cursos) {
            if (curso.getId() != null && curso.getId() > maxId) {
                maxId = curso.getId();
            }
        }
        return maxId + 1;
    }
}
