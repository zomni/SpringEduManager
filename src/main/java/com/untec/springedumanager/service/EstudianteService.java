package com.untec.springedumanager.service;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante guardar(Estudiante estudiante) {

        if (estudiante.getId() != null) {
            Optional<Estudiante> existente = estudianteRepository.findById(estudiante.getId());

            if (existente.isPresent()) {
                Estudiante estudianteBD = existente.get();
                estudianteBD.setNombre(estudiante.getNombre());
                estudianteBD.setEmail(estudiante.getEmail());
                return estudianteRepository.save(estudianteBD);
            }
        }

        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Optional<Estudiante> actualizar(Long id, Estudiante actualizado) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);

        if (estudianteOptional.isPresent()) {
            Estudiante estudiante = estudianteOptional.get();
            estudiante.setNombre(actualizado.getNombre());
            estudiante.setEmail(actualizado.getEmail());
            return Optional.of(estudianteRepository.save(estudiante));
        }

        return Optional.empty();
    }
}