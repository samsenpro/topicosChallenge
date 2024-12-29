package com.software.integration.service;

import com.software.integration.dto.DatosRegistroUsuario;
import com.software.integration.model.Usuario;
import com.software.integration.model.Usuario.*;
import com.software.integration.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistro) {
        // Verificar si el email ya está en uso
        if (usuarioRepository.findByEmail(datosRegistro.email()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(datosRegistro.nombre());
        usuario.setEmail(datosRegistro.email());
        usuario.setPassword(passwordEncoder.encode(datosRegistro.clave()));

        // Asignar rol (puedes asignar un rol por defecto si no se proporciona)
        Rol rol;
        try {
            rol = Rol.valueOf(datosRegistro.rol());
        } catch (IllegalArgumentException e) {
            rol = Rol.ESTUDIANTE; // Rol por defecto
        }
        usuario.setRol(rol);

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
}
