package com.software.integration.dto;

import com.software.integration.model.Topico.*;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        StatusTopico status
) {}