package com.untec.springedumanager.controller;

import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class EstudianteViewController {

    private final EstudianteService estudianteService;

    public EstudianteViewController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model, Principal principal) {
        model.addAttribute("estudiantes", estudianteService.listarTodos());
        model.addAttribute("usuario", principal.getName());
        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String mostrarFormulario(Model model, Principal principal) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("usuario", principal.getName());
        return "estudiante-form";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model, Principal principal) {
        Optional<Estudiante> estudiante = estudianteService.buscarPorId(id);

        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            model.addAttribute("usuario", principal.getName());
            return "estudiante-form";
        }

        return "redirect:/estudiantes";
    }

    @PostMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }
}