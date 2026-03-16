package com.untec.springedumanager.service;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public void guardar(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }
}