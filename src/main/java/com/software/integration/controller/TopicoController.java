package com.software.integration.controller;

import com.software.integration.dto.DatosActualizarTopico;
import com.software.integration.dto.DatosListadoTopico;
import com.software.integration.dto.DatosRegistroTopico;
import com.software.integration.dto.DatosRespuestaTopico;
import com.software.integration.model.Topico;
import com.software.integration.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Topico topicoRegistrado = topicoService.registrar(datosRegistroTopico);
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topicoRegistrado);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(Pageable paginacion) {
        Page<Topico> topicosPage = topicoService.listar(paginacion);
        Page<DatosListadoTopico> topicosDTO = topicosPage.map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.buscarPorId(id);
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topicoActualizado = topicoService.actualizar(id, datosActualizarTopico);
        DatosRespuestaTopico respuesta = new DatosRespuestaTopico(topicoActualizado);
        return ResponseEntity.ok(respuesta);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
