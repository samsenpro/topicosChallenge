package com.software.integration.service;

import com.software.integration.dto.DatosActualizarTopico;
import com.software.integration.dto.DatosRegistroTopico;
import com.software.integration.model.Topico;
import com.software.integration.repository.CursoRepository;
import com.software.integration.repository.TopicoRepository;
import com.software.integration.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Método registrar actualizado para aceptar DatosRegistroTopico
    public Topico registrar(DatosRegistroTopico datosRegistroTopico) {
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje");
        }

        var autor = usuarioRepository.findById(datosRegistroTopico.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + datosRegistroTopico.autorId()));
        var curso = cursoRepository.findById(datosRegistroTopico.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + datosRegistroTopico.cursoId()));

        var topico = new Topico(datosRegistroTopico, autor, curso);

        return topicoRepository.save(topico);
    }

    public Page<Topico> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion);
    }

    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
    }

    public Topico actualizar(Long id, DatosActualizarTopico datos) {
        var topico = buscarPorId(id);

        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }
        if (datos.status() != null) {
            topico.setStatus(datos.status());
        }

        return topicoRepository.save(topico);
    }

    public void eliminar(Long id) {
        var topico = buscarPorId(id);
        topicoRepository.delete(topico);
    }
}
