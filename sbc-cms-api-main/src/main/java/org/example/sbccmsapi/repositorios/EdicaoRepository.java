package org.example.sbccmsapi.repositorios;

import org.example.sbccmsapi.entidades.Edicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EdicaoRepository extends JpaRepository<Edicao, Long> {
  Page<Edicao> findAllByEventoId(Long eventoId, Pageable pageable);

  Optional<Edicao> findByIdAndEventoId(Long id, Long eventoId);
}
