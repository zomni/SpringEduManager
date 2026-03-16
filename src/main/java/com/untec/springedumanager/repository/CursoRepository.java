package com.untec.springedumanager.repository;

import com.untec.springedumanager.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}