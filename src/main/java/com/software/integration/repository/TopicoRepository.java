package com.software.integration.repository;

import com.software.integration.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findById(Long id); // Eliminado el filtro por activo
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
