package org.example.sbccmsapi.servicos;

import org.example.sbccmsapi.dtos.requests.CriarEdicaoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEdicoesResponse;
import org.example.sbccmsapi.entidades.Edicao;
import org.example.sbccmsapi.repositorios.EdicaoRepository;
import org.example.sbccmsapi.repositorios.EventoRepository;
import org.example.sbccmsapi.repositorios.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EdicaoService {
  private final EdicaoRepository edicaoRepository;
  private final EventoRepository eventoRepository;
  private final UsuarioRepository usuarioRepository;

  public EdicaoService(final EdicaoRepository edicaoRepository, final EventoRepository eventoRepository, final UsuarioRepository usuarioRepository) {
    this.edicaoRepository = Objects.requireNonNull(edicaoRepository);
    this.eventoRepository = Objects.requireNonNull(eventoRepository);
    this.usuarioRepository = Objects.requireNonNull(usuarioRepository);
  }

  public void criar(final Long eventoId, final CriarEdicaoRequest request) {
    var evento = eventoRepository.findById(eventoId).orElseThrow();

    edicaoRepository.save(Edicao.from(request, evento));
  }

  public Page<ObterEdicoesResponse> obterTodosPaginado(Long eventoId, Pageable pageable) {
    return edicaoRepository
        .findAllByEventoId(eventoId, pageable)
        .map(Edicao::toResponse);
  }

  public ObterEdicoesResponse obter(Long eventoId, Long id) {
    return edicaoRepository
        .findByIdAndEventoId(id, eventoId)
        .map(Edicao::toResponse)
        .orElseThrow();
  }

  public void editar(Long eventoId, Long id, CriarEdicaoRequest request) {
    edicaoRepository
        .findByIdAndEventoId(id, eventoId)
        .map(
            edicao -> {
              edicao.setNumero(request.numero());
              edicao.setAno(request.ano());
              edicao.setDataInicial(request.dataInicial());
              edicao.setDataFinal(request.dataFinal());
              edicao.setCidade(request.cidade());
              return edicao;
            }
        ).ifPresent(edicaoRepository::save);
  }

  public void deletar(Long eventoId, Long id) {
    edicaoRepository
        .findByIdAndEventoId(id, eventoId)
        .ifPresent(edicaoRepository::delete);
  }

  public void configurarOrganizador(Long eventoId, Long edicaoId, Long organizadorId) {
    edicaoRepository
        .findByIdAndEventoId(edicaoId, eventoId)
        .map(edicao -> {
          var organizador = this.usuarioRepository.findById(organizadorId).orElseThrow();
          edicao.setOrganizador(organizador);
          return edicao;
        })
        .ifPresent(edicaoRepository::save);
  }
}
