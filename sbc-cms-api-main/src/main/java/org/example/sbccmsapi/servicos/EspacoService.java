package org.example.sbccmsapi.servicos;

import org.example.sbccmsapi.dtos.requests.CriarEspacoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEspacoResponse;
import org.example.sbccmsapi.entidades.Espaco;
import org.example.sbccmsapi.repositorios.EspacoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EspacoService {
  private final EspacoRepository repository;

  public EspacoService(final EspacoRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  public Page<ObterEspacoResponse> obterTodosPaginado(final Pageable pageable) {
    return repository
        .findAll(pageable)
        .map(Espaco::toResponse);
  }

  public ObterEspacoResponse obter(final Long id) {
    return repository
        .findById(id)
        .map(Espaco::toResponse)
        .orElseThrow();
  }

  public void criar(final CriarEspacoRequest request) {
    this.repository.save(Espaco.from(request));
  }

  public void editar(final Long id, final CriarEspacoRequest request) {
    repository
        .findById(id)
        .map(espaco -> {
          espaco.setNome(request.nome());
          espaco.setLocalizacao(request.localizacao());
          espaco.setCapacidade(request.capacidade());
          espaco.setRecursos(request.recursos());
          return espaco;
        })
        .ifPresent(repository::save);
  }

  public void deletar(final Long id) {
    repository
        .findById(id)
        .ifPresent(repository::delete);
  }
}
