package org.example.sbccmsapi.repositorios;

import org.example.sbccmsapi.entidades.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
  Page<Evento> findAll(Pageable pageable);
}
