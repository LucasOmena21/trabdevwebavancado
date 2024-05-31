package org.example.sbccmsapi.servicos;

import org.example.sbccmsapi.dtos.requests.CriarAtividadeRequest;
import org.example.sbccmsapi.dtos.responses.ObterAtividadeResponse;
import org.example.sbccmsapi.entidades.Atividade;
import org.example.sbccmsapi.repositorios.AtividadeRepository;
import org.example.sbccmsapi.repositorios.EdicaoRepository;
import org.example.sbccmsapi.repositorios.EspacoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AtividadeService {
  private final AtividadeRepository atividadeRepository;
  private final EdicaoRepository edicaoRepository;
  private final EspacoRepository espacoRepository;

  public AtividadeService(
      final AtividadeRepository atividadeRepository,
      final EdicaoRepository edicaoRepository,
      final EspacoRepository espacoRepository
  ) {
    this.atividadeRepository = Objects.requireNonNull(atividadeRepository);
    this.edicaoRepository = Objects.requireNonNull(edicaoRepository);
    this.espacoRepository = Objects.requireNonNull(espacoRepository);
  }

  public Page<ObterAtividadeResponse> obterTodosPaginado(final Long eventoId, final Long edicaoId, final Pageable pageable) {
    var edicao = edicaoRepository.findByIdAndEventoId(edicaoId, eventoId).orElseThrow();

    return atividadeRepository.
        findAllByEdicaoId(edicao.getId(), pageable)
        .map(Atividade::toResponse);
  }

  public ObterAtividadeResponse obter(final Long eventoId, final Long edicaoId, final Long id) {
    var edicao = edicaoRepository.findByIdAndEventoId(edicaoId, eventoId).orElseThrow();

    var atividade = atividadeRepository.findByIdAndEdicaoId(id, edicao.getId()).orElseThrow();
    return new ObterAtividadeResponse(
        atividade.getId(),
        atividade.getNome(),
        atividade.getTipo(),
        atividade.getDescricao(),
        atividade.getData(),
        atividade.getHorarioInicial(),
        atividade.getHorarioFinal(),
        atividade.getLocal()
    );
  }

  public void criar(final CriarAtividadeRequest request, final Long eventoId, final Long edicaoId) {
    var edicao = edicaoRepository.findByIdAndEventoId(edicaoId, eventoId).orElseThrow();
    var espaco = espacoRepository.findById(request.localId()).orElseThrow();
    atividadeRepository.save(Atividade.from(request, edicao, espaco));
  }

  public void editar(final Long eventoId, final Long edicaoId, final Long id, final CriarAtividadeRequest request) {
    var local = espacoRepository.findById(request.localId()).orElseThrow();
    var edicao = edicaoRepository.findByIdAndEventoId(edicaoId, eventoId).orElseThrow();

    atividadeRepository
        .findByIdAndEdicaoId(id, edicao.getId())
        .map(
            atividade -> {
              atividade.setNome(request.nome());
              atividade.setTipo(request.tipo());
              atividade.setDescricao(request.descricao());
              atividade.setData(request.data());
              atividade.setHorarioInicial(request.horarioInicial());
              atividade.setHorarioFinal(request.horarioFinal());
              atividade.setLocal(local);
              return atividade;
            }
        ).ifPresent(atividadeRepository::save);
  }

  public void deletar(final Long eventoId, final Long edicaoId, final Long id) {
    var edicao = edicaoRepository.findByIdAndEventoId(edicaoId, eventoId).orElseThrow();

    atividadeRepository.findByIdAndEdicaoId(id, edicao.getId())
        .ifPresent(atividadeRepository::delete);
  }
}
