package org.example.sbccmsapi.controllers;

import org.example.sbccmsapi.dtos.requests.CriarEspacoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEspacoResponse;
import org.example.sbccmsapi.servicos.EspacoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/espacos")
public class EspacoController {
  private final EspacoService service;

  public EspacoController(final EspacoService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping
  public ResponseEntity<Page<ObterEspacoResponse>> obterTodos(
      @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
      @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
      @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
      @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction) {
    final var pageable = PageRequest.of(
        page,
        perPage,
        direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
        sort
    );

    return ResponseEntity.ok(service.obterTodosPaginado(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObterEspacoResponse> obter(@PathVariable final Long id) {
    return ResponseEntity.ok(service.obter(id));
  }

  @PostMapping
  public void criar(@RequestBody final CriarEspacoRequest request) {
    service.criar(request);
  }

  @PutMapping("/{id}")
  public void editar(@PathVariable final Long id, @RequestBody final CriarEspacoRequest request) {
    service.editar(id, request);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable final Long id) {
    service.deletar(id);
  }
}
