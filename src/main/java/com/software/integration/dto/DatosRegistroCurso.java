package com.software.integration.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroCurso(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
        String nombre,

        @NotBlank(message = "La categoría es obligatoria")
        @Size(max = 100, message = "La categoría no debe exceder 100 caracteres")
        String categoria
) {}
