package com.software.integration.dto;

import jakarta.validation.constraints.Size;

public record DatosActualizarCurso(
        Long id,

        @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
        String nombre,

        @Size(max = 100, message = "La categoría no debe exceder 100 caracteres")
        String categoria
) {}