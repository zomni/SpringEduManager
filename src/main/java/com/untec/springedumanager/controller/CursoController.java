package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {

        Optional<Curso> curso =
                cursoService.buscarPorId(id);

        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(
            @PathVariable Long id,
            @RequestBody Curso curso
    ) {

        Optional<Curso> actualizado =
                cursoService.actualizar(id, curso);

        return actualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        Optional<Curso> curso =
                cursoService.buscarPorId(id);

        if (curso.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{cursoId}/estudiantes/{estudianteId}")
    public ResponseEntity<Curso> inscribir(
            @PathVariable Long cursoId,
            @PathVariable Long estudianteId
    ) {

        Optional<Curso> curso =
                cursoService.inscribirEstudiante(
                        cursoId,
                        estudianteId
                );

        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}