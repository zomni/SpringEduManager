package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EstudianteViewController {

    private final EstudianteService estudianteService;

    public EstudianteViewController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.listarTodos());
        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante-form";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }
}