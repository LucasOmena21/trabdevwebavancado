package org.example.sbccmsapi.controllers;

import org.example.sbccmsapi.dtos.requests.CriarUsuarioRequest;
import org.example.sbccmsapi.dtos.requests.FavoritarRequest;
import org.example.sbccmsapi.servicos.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  private final UsuarioService service;

  public UsuarioController(final UsuarioService service) {
    this.service = Objects.requireNonNull(service);
  }

  @PostMapping
  public void criar(@RequestBody final CriarUsuarioRequest request) {
    service.criar(request);
  }

  @PostMapping("/{id}/favoritar")
  public void favoritar(@PathVariable final Long id, @RequestBody final FavoritarRequest request) {
    service.favoritar(id, request.atividadeId());
  }

  @PostMapping("/{id}/desfavoritar")
  public void desfavoritar(@PathVariable final Long id, @RequestBody final FavoritarRequest request) {
    service.desfavoritar(id, request.atividadeId());
  }
}
