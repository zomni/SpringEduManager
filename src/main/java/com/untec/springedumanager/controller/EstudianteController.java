package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.CursoRepository;
import com.untec.springedumanager.repository.EstudianteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    // 🔹 ATRIBUTOS (van aquí, arriba de todo)
    private final EstudianteRepository repo;
    private final CursoRepository cursoRepo;

    // 🔹 CONSTRUCTOR (inyección de dependencias)
    public EstudianteController(EstudianteRepository repo,
                                CursoRepository cursoRepo) {
        this.repo = repo;
        this.cursoRepo = cursoRepo;
    }

    // 🔹 ENDPOINTS

    @GetMapping
    public List<Estudiante> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante e) {
        return repo.save(e);
    }

    // 🔹 ENDPOINT PARA INSCRIBIR ESTUDIANTE EN CURSO
    @PostMapping("/{estudianteId}/cursos/{cursoId}")
    public Estudiante inscribirEnCurso(@PathVariable Long estudianteId,
                                       @PathVariable Long cursoId) {

        var estudiante = repo.findById(estudianteId).orElseThrow();
        var curso = cursoRepo.findById(cursoId).orElseThrow();

        estudiante.getCursos().add(curso);
        curso.getEstudiantes().add(estudiante);

        return repo.save(estudiante);
    }
}