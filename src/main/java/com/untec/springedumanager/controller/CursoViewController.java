package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.EstudianteRepository;
import com.untec.springedumanager.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CursoViewController {

    private final CursoService cursoService;
    private final EstudianteRepository estudianteRepository;

    public CursoViewController(CursoService cursoService, EstudianteRepository estudianteRepository) {
        this.cursoService = cursoService;
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model, Principal principal) {
        model.addAttribute("cursos", cursoService.listarTodos());
        model.addAttribute("usuario", principal.getName());
        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String mostrarFormulario(Model model, Principal principal) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("usuario", principal.getName());
        return "curso-form";
    }

    @PostMapping("/cursos/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model, Principal principal) {
        Optional<Curso> curso = cursoService.buscarPorId(id);

        if (curso.isPresent()) {
            model.addAttribute("curso", curso.get());
            model.addAttribute("usuario", principal.getName());
            return "curso-form";
        }

        return "redirect:/cursos";
    }

    @GetMapping("/cursos/{id}/inscribir")
    public String mostrarInscripcion(@PathVariable Long id, Model model, Principal principal) {
        Optional<Curso> curso = cursoService.buscarPorId(id);

        model.addAttribute("curso", curso.get());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("usuario", principal.getName());

        return "inscribir";
    }

    @PostMapping("/cursos/{cursoId}/inscribir/{estudianteId}")
    public String inscribir(@PathVariable Long cursoId, @PathVariable Long estudianteId) {
        Optional<Curso> cursoOptional = cursoService.buscarPorId(cursoId);
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudianteId);

        if (cursoOptional.isPresent() && estudianteOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            Estudiante estudiante = estudianteOptional.get();

            curso.getEstudiantes().add(estudiante);
            estudiante.getCursos().add(curso);

            cursoService.guardar(curso);
            estudianteRepository.save(estudiante);
        }

        return "redirect:/cursos";
    }

    @PostMapping("/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }
}