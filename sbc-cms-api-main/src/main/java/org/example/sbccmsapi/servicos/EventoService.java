package org.example.sbccmsapi.servicos;

import org.example.sbccmsapi.dtos.requests.CriarEventoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEventosResponse;
import org.example.sbccmsapi.entidades.Evento;
import org.example.sbccmsapi.repositorios.EventoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventoService {
  private final EventoRepository repository;

  public EventoService(final EventoRepository repository) {
    this.repository = Objects.requireNonNull(repository);
  }

  public Page<ObterEventosResponse> obterTodosPaginado(final Pageable pageable) {
    return repository
        .findAll(pageable)
        .map(Evento::toResponse);
  }

  public ObterEventosResponse obter(final Long id) {
    return repository
        .findById(id)
        .map(Evento::toResponse)
        .orElseThrow();
  }

  public void criar(final CriarEventoRequest request) {
    repository.save(Evento.from(request));
  }

  public void editar(final Long id, final CriarEventoRequest request) {
    repository
        .findById(id)
        .map(evento -> {
          evento.setNome(request.nome());
          evento.setSigla(request.sigla());
          evento.setDescricao(request.descricao());
          return evento;
        })
        .ifPresent(repository::save);
  }

  public void deletar(Long id) {
    repository
        .findById(id)
        .ifPresent(repository::delete);
  }
}
