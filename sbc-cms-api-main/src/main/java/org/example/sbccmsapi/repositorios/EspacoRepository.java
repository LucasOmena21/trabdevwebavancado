package org.example.sbccmsapi.repositorios;

import org.example.sbccmsapi.entidades.Espaco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspacoRepository extends JpaRepository<Espaco, Long> {
  Page<Espaco> findAll(Pageable pageable);
}
