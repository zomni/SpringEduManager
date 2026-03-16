package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.EstudianteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);

        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(
            @PathVariable Long id,
            @RequestBody Estudiante estudianteActualizado
    ) {

        Optional<Estudiante> estudianteOptional =
                estudianteRepository.findById(id);

        if (estudianteOptional.isPresent()) {

            Estudiante estudiante = estudianteOptional.get();

            estudiante.setNombre(estudianteActualizado.getNombre());
            estudiante.setEmail(estudianteActualizado.getEmail());

            Estudiante guardado =
                    estudianteRepository.save(estudiante);

            return ResponseEntity.ok(guardado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Optional<Estudiante> estudianteOptional =
                estudianteRepository.findById(id);

        if (estudianteOptional.isPresent()) {

            estudianteRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}