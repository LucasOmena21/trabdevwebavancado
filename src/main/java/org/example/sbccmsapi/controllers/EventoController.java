package org.example.sbccmsapi.controllers;

import org.example.sbccmsapi.dtos.requests.CriarEventoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEventosResponse;
import org.example.sbccmsapi.servicos.EventoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/eventos")
public class EventoController {
  private final EventoService service;

  public EventoController(final EventoService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping
  public ResponseEntity<Page<ObterEventosResponse>> obterTodosPaginado(
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

    return ResponseEntity.ok(service.obterTodosPaginado(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObterEventosResponse> obter(@PathVariable final Long id) {
    return ResponseEntity.ok(service.obter(id));
  }

  @PostMapping
  public void criar(@RequestBody final CriarEventoRequest request) {
    service.criar(request);
  }

  @PutMapping("/{id}")
  public void editar(@PathVariable Long id, @RequestBody final CriarEventoRequest request) {
    service.editar(id, request);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    service.deletar(id);
  }
}
