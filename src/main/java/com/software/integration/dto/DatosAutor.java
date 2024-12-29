package com.software.integration.dto;

import com.software.integration.model.Usuario;

public
record DatosAutor(Long id, String nombre) {
    public DatosAutor(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
