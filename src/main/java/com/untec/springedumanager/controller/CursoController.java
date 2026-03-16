package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.repository.CursoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository repo;

    public CursoController(CursoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Curso> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Curso crear(@RequestBody Curso c) {
        return repo.save(c);
    }
}