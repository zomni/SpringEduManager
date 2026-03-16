package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CursoViewController {

    private final CursoService cursoService;

    public CursoViewController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso-form";
    }

    @PostMapping("/cursos/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }
}