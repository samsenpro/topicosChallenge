package com.software.integration.dto;

import com.software.integration.model.Cursos;

public record DatosRespuestaCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosRespuestaCurso(Cursos curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
