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
        if (usuarioRepository.findByEmail(datosRegistro.email()).isPresent()) {
            throw new IllegalArgumentException("El email ya está en uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(datosRegistro.nombre());
        usuario.setEmail(datosRegistro.email());
        usuario.setPassword(passwordEncoder.encode(datosRegistro.clave()));

        Rol rol;
        try {
            rol = Rol.valueOf(datosRegistro.rol());
        } catch (IllegalArgumentException e) {
            rol = Rol.ESTUDIANTE;
        }
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }
}
