package controllers;

import entities.Curso;
import services.CursoService;

import java.util.List;
import java.util.Optional;

public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    public Curso create(Curso curso) {
        return service.create(curso);
    }

    public List<Curso> listAll() {
        return service.listAll();
    }

    public Optional<Curso> findOne(Long id) {
        return service.findOne(id);
    }

    public boolean update(Long id, Curso curso) {
        return service.update(id, curso);
    }

    public boolean delete(Long id) {
        return service.delete(id);
    }
}
