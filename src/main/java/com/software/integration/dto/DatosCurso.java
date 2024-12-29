package com.software.integration.dto;

import com.software.integration.model.Cursos;

public record DatosCurso(Long id, String nombre) {
    public DatosCurso(Cursos curso) {
        this(curso.getId(), curso.getNombre());
    }
}