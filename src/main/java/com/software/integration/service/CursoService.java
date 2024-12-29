package com.software.integration.service;

import com.software.integration.dto.DatosActualizarCurso;
import com.software.integration.dto.DatosRegistroCurso;
import com.software.integration.model.Cursos;
import com.software.integration.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Cursos registrar(DatosRegistroCurso datosRegistroCurso) {
        if (cursoRepository.existsByNombreAndCategoria(datosRegistroCurso.nombre(), datosRegistroCurso.categoria())) {
            throw new ValidationException("Ya existe un curso con el mismo nombre y categoría");
        }

        Cursos curso = new Cursos(datosRegistroCurso.nombre(), datosRegistroCurso.categoria());
        return cursoRepository.save(curso);
    }

    public Page<Cursos> listar(Pageable paginacion) {
        return cursoRepository.findAll(paginacion);
    }

    public Cursos buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + id));
    }

    public Cursos actualizar(DatosActualizarCurso datos) {
        Cursos curso = buscarPorId(datos.id());

        if (datos.nombre() != null && !datos.nombre().isBlank()) {
            curso.setNombre(datos.nombre());
        }

        if (datos.categoria() != null && !datos.categoria().isBlank()) {
            curso.setCategoria(datos.categoria());
        }

        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        Cursos curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }
}
