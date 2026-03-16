package com.untec.springedumanager.service;

import com.untec.springedumanager.model.Curso;
import com.untec.springedumanager.model.Estudiante;
import com.untec.springedumanager.repository.CursoRepository;
import com.untec.springedumanager.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;

    public CursoService(
            CursoRepository cursoRepository,
            EstudianteRepository estudianteRepository
    ) {
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso guardar(Curso curso) {

        if (curso.getId() != null) {
            Optional<Curso> existente = cursoRepository.findById(curso.getId());

            if (existente.isPresent()) {
                Curso cursoBD = existente.get();
                cursoBD.setNombre(curso.getNombre());
                return cursoRepository.save(cursoBD);
            }
        }

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            for (Estudiante estudiante : curso.getEstudiantes()) {
                estudiante.getCursos().remove(curso);
                estudianteRepository.save(estudiante);
            }

            curso.getEstudiantes().clear();
            cursoRepository.save(curso);

            cursoRepository.delete(curso);
        }
    }

    public Optional<Curso> actualizar(Long id, Curso actualizado) {

        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {

            Curso curso = cursoOptional.get();

            curso.setNombre(actualizado.getNombre());

            return Optional.of(cursoRepository.save(curso));
        }

        return Optional.empty();
    }

    public Optional<Curso> inscribirEstudiante(
            Long cursoId,
            Long estudianteId
    ) {

        Optional<Curso> cursoOptional =
                cursoRepository.findById(cursoId);

        Optional<Estudiante> estudianteOptional =
                estudianteRepository.findById(estudianteId);

        if (cursoOptional.isPresent()
                && estudianteOptional.isPresent()) {

            Curso curso = cursoOptional.get();
            Estudiante estudiante = estudianteOptional.get();

            curso.getEstudiantes().add(estudiante);
            estudiante.getCursos().add(curso);

            cursoRepository.save(curso);
            estudianteRepository.save(estudiante);

            return Optional.of(curso);
        }

        return Optional.empty();
    }
}