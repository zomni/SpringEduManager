package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody Curso cursoActualizado) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            curso.setNombre(cursoActualizado.getNombre());
            Curso cursoGuardado = cursoRepository.save(curso);
            return ResponseEntity.ok(cursoGuardado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}