package org.example.sbccmsapi.servicos;

import org.example.sbccmsapi.dtos.requests.CriarUsuarioRequest;
import org.example.sbccmsapi.dtos.responses.ObterUsuariosResponse;
import org.example.sbccmsapi.entidades.Usuario;
import org.example.sbccmsapi.repositorios.AtividadeRepository;
import org.example.sbccmsapi.repositorios.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioService {
  private final UsuarioRepository repository;
  private final AtividadeRepository atividadeRepository;

  public UsuarioService(final UsuarioRepository repository, final AtividadeRepository atividadeRepository) {
    this.repository = Objects.requireNonNull(repository);
    this.atividadeRepository = Objects.requireNonNull(atividadeRepository);
  }

  public Page<ObterUsuariosResponse> obterTodosPaginado(final Pageable pageable) {
    return repository
        .findAll(pageable)
        .map(Usuario::toResponse);
  }

  public void criar(final CriarUsuarioRequest request) {
    repository.save(Usuario.from(request));
  }

  public void favoritar(final Long id, final Long atividadeId) {
    var atividade = atividadeRepository.findById(atividadeId).orElseThrow();

    repository
        .findById(id)
        .ifPresent(
            usuario -> {
              usuario.favoritar(atividade);
              repository.save(usuario);
            });
  }

  public void desfavoritar(final Long id, final Long atividadeId) {
    var atividade = atividadeRepository.findById(atividadeId).orElseThrow();

    repository
        .findById(id)
        .ifPresent(
            usuario -> {
              usuario.desfavoritar(atividade);
              repository.save(usuario);
            });
  }
}
