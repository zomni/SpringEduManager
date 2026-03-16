package com.untec.springedumanager.service;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public void guardar(Curso curso) {
        cursoRepository.save(curso);
    }
}