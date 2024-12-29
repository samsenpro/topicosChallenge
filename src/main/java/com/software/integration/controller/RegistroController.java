package com.software.integration.controller;

import com.software.integration.dto.DatosRegistroUsuario;
import com.software.integration.dto.DatosRespuestaRegistro;
import com.software.integration.model.Usuario;
import com.software.integration.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(datosRegistroUsuario);
            DatosRespuestaRegistro respuesta = new DatosRespuestaRegistro(
                    nuevoUsuario.getId(),
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getEmail(),
                    nuevoUsuario.getRol().name()
            );
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al registrar el usuario.");
        }
    }
}
