package org.example.sbccmsapi.controllers;

import org.example.sbccmsapi.dtos.requests.CriarAtividadeRequest;
import org.example.sbccmsapi.dtos.responses.ObterAtividadeResponse;
import org.example.sbccmsapi.servicos.AtividadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/eventos/{eventoId}/edicoes/{edicaoId}/atividades")
public class AtividadeController {
  private final AtividadeService service;

  public AtividadeController(final AtividadeService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping
  public ResponseEntity<Page<ObterAtividadeResponse>> obterTodos(
      @PathVariable("eventoId") final Long eventoId,
      @PathVariable("edicaoId") final Long edicaoId,
      @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
      @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
      @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
      @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
  ) {
    final var pageable = PageRequest.of(
        page,
        perPage,
        direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
        sort
    );

    return ResponseEntity.ok(service.obterTodosPaginado(eventoId, edicaoId, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObterAtividadeResponse> obter(@PathVariable("eventoId") final Long eventoId, @PathVariable("edicaoId") final Long edicaoId, @PathVariable final Long id) {
    return ResponseEntity.ok(service.obter(eventoId, edicaoId, id));
  }

  @PostMapping
  public void criar(@PathVariable("eventoId") final Long eventoId, @PathVariable("edicaoId") final Long edicaoId, @RequestBody final CriarAtividadeRequest request) {
    service.criar(request, eventoId, edicaoId);
  }

  @PutMapping("/{id}")
  public void editar(@PathVariable("eventoId") final Long eventoId, @PathVariable("edicaoId") final Long edicaoId, @PathVariable final Long id, @RequestBody final CriarAtividadeRequest request) {
    service.editar(eventoId, edicaoId, id, request);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable("eventoId") final Long eventoId, @PathVariable("edicaoId") final Long edicaoId, @PathVariable final Long id) {
    service.deletar(eventoId, edicaoId, id);
  }
}
