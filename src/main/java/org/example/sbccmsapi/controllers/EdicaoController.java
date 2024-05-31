package org.example.sbccmsapi.controllers;

import org.example.sbccmsapi.dtos.requests.ConfigurarOrganizadorRequest;
import org.example.sbccmsapi.dtos.requests.CriarEdicaoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEdicoesResponse;
import org.example.sbccmsapi.servicos.EdicaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/eventos/{eventoId}/edicoes")
public class EdicaoController {
  private final EdicaoService service;

  public EdicaoController(final EdicaoService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping
  public ResponseEntity<Page<ObterEdicoesResponse>> obterTodos(
      @PathVariable("eventoId") final Long eventoId,
      @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
      @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
      @RequestParam(name = "sort", required = false, defaultValue = "dataInicial") final String sort,
      @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction) {
    final var pageable = PageRequest.of(
        page,
        perPage,
        direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
        sort
    );

    return ResponseEntity.ok(service.obterTodosPaginado(eventoId, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObterEdicoesResponse> obter(@PathVariable("eventoId") final Long eventoId, @PathVariable final Long id) {
    return ResponseEntity.ok(service.obter(eventoId, id));
  }

  @PostMapping
  public void criar(@PathVariable("eventoId") final Long eventoId, @RequestBody final CriarEdicaoRequest request) {
    service.criar(eventoId, request);
  }

  @PutMapping("/{id}")
  public void editar(@PathVariable("eventoId") final Long eventoId, @PathVariable final Long id, @RequestBody final CriarEdicaoRequest request) {
    service.editar(eventoId, id, request);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable("eventoId") final Long eventoId, @PathVariable final Long id) {
    service.deletar(eventoId, id);
  }

  @PostMapping("/{id}/organizador")
  public void configurarOrganizador(@PathVariable("eventoId") final Long eventoId, @PathVariable final Long id, @RequestBody final ConfigurarOrganizadorRequest request) {
    service.configurarOrganizador(eventoId, id, request.organizadorId());
  }
}
