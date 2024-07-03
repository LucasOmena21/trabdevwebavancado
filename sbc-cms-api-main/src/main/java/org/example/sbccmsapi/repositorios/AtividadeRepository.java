package org.example.sbccmsapi.repositorios;

import org.example.sbccmsapi.entidades.Atividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
  Page<Atividade> findAllByEdicaoId(Long edicaoId, Pageable pageable);

  Optional<Atividade> findByIdAndEdicaoId(Long id, Long edicaoId);
}
