package org.example.sbccmsapi.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.sbccmsapi.dtos.requests.CriarUsuarioRequest;
import org.example.sbccmsapi.dtos.requests.FavoritarRequest;
import org.example.sbccmsapi.dtos.responses.ObterUsuariosResponse;
import org.example.sbccmsapi.servicos.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "Usuário", description = "Gerencia os usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  private final UsuarioService service;

  public UsuarioController(final UsuarioService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping
  public ResponseEntity<Page<ObterUsuariosResponse>> obterTodosPaginado(
      @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
      @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
      @RequestParam(name = "sort", required = false, defaultValue = "nome") final String sort,
      @RequestParam(name = "dir", required = false, defaultValue = "asc") final String directione
  ) {

    final var pageable = PageRequest.of(
        page,
        perPage,
        directione.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
        sort
    );

    return ResponseEntity.ok(service.obterTodosPaginado(pageable));
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
