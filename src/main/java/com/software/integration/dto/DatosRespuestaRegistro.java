package com.software.integration.dto;

public class DatosRespuestaRegistro {
    private Long id;
    private String nombre;
    private String email;
    private String rol;

    public DatosRespuestaRegistro(Long id, String nombre, String email, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }
}
