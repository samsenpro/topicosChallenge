package com.software.integration.repository;

import com.software.integration.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Cursos, Long> {
    boolean existsByNombreAndCategoria(String nombre, String categoria);
}