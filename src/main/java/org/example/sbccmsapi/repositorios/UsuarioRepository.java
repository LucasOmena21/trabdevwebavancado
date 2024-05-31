package org.example.sbccmsapi.repositorios;

import org.example.sbccmsapi.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
