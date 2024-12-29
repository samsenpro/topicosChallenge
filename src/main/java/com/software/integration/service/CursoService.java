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

    /**
     * Registrar un nuevo curso.
     *
     * @param datosRegistroCurso Datos necesarios para registrar un curso.
     * @return El curso registrado.
     */
    public Cursos registrar(DatosRegistroCurso datosRegistroCurso) {
        // Verificar si ya existe un curso con el mismo nombre y categoría
        if (cursoRepository.existsByNombreAndCategoria(datosRegistroCurso.nombre(), datosRegistroCurso.categoria())) {
            throw new ValidationException("Ya existe un curso con el mismo nombre y categoría");
        }

        Cursos curso = new Cursos(datosRegistroCurso.nombre(), datosRegistroCurso.categoria());
        return cursoRepository.save(curso);
    }

    /**
     * Listar todos los cursos con paginación.
     *
     * @param paginacion Parámetros de paginación.
     * @return Página de cursos.
     */
    public Page<Cursos> listar(Pageable paginacion) {
        return cursoRepository.findAll(paginacion);
    }

    /**
     * Buscar un curso por su ID.
     *
     * @param id ID del curso.
     * @return El curso encontrado.
     */
    public Cursos buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado con ID: " + id));
    }

    /**
     * Actualizar un curso existente.
     *
     * @param datos Datos para actualizar el curso.
     * @return El curso actualizado.
     */
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

    /**
     * Eliminar un curso por su ID.
     *
     * @param id ID del curso a eliminar.
     */
    public void eliminar(Long id) {
        Cursos curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }
}
