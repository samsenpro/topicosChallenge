package com.software.integration.dto;

import com.software.integration.model.Topico;
import com.software.integration.model.Topico.*;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        DatosAutor autor,
        DatosCurso curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new DatosAutor(topico.getAutor()),
                new DatosCurso(topico.getCurso()));
    }
}