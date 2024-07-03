package org.example.sbccmsapi.entidades;

import jakarta.persistence.*;
import org.example.sbccmsapi.dtos.requests.CriarAtividadeRequest;
import org.example.sbccmsapi.dtos.responses.ObterAtividadeResponse;
import org.example.sbccmsapi.enums.TipoAtividade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "atividades")
public class Atividade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private TipoAtividade tipo;
  private String descricao;
  private LocalDate data;
  @Column(name = "horario_inicial")
  private LocalTime horarioInicial;
  @Column(name = "horario_final")
  private LocalTime horarioFinal;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Espaco local;
  @ManyToOne(cascade = CascadeType.ALL)
  private Edicao edicao;

  public Atividade() {
  }

  public Atividade(
      Long id,
      String nome,
      TipoAtividade tipo,
      String descricao,
      LocalDate data,
      LocalTime horarioInicial,
      LocalTime horarioFinal,
      Espaco local,
      Edicao edicao
  ) {
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.descricao = descricao;
    this.data = data;
    this.horarioInicial = horarioInicial;
    this.horarioFinal = horarioFinal;
    this.local = local;
    this.edicao = edicao;
  }

  public static Atividade from(final CriarAtividadeRequest request, final Edicao edicao, final Espaco espaco) {
    return new Atividade(
        null,
        request.nome(),
        request.tipo(),
        request.descricao(),
        request.data(),
        request.horarioInicial(),
        request.horarioFinal(),
        espaco,
        edicao
    );
  }


  public ObterAtividadeResponse toResponse() {
    return new ObterAtividadeResponse(
        getId(),
        getNome(),
        getTipo(),
        getDescricao(),
        getData(),
        getHorarioInicial(),
        getHorarioFinal(),
        getLocal()
    );
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public TipoAtividade getTipo() {
    return tipo;
  }

  public void setTipo(final TipoAtividade tipo) {
    this.tipo = tipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(final String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(final LocalDate data) {
    this.data = data;
  }

  public LocalTime getHorarioInicial() {
    return horarioInicial;
  }

  public void setHorarioInicial(final LocalTime horarioInicial) {
    this.horarioInicial = horarioInicial;
  }

  public LocalTime getHorarioFinal() {
    return horarioFinal;
  }

  public void setHorarioFinal(final LocalTime horarioFinal) {
    this.horarioFinal = horarioFinal;
  }

  public Espaco getLocal() {
    return local;
  }

  public void setLocal(final Espaco local) {
    this.local = local;
  }

  public Edicao getEdicao() {
    return edicao;
  }

  public void setEdicao(final Edicao edicao) {
    this.edicao = edicao;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Atividade atividade = (Atividade) o;
    return Objects.equals(getId(), atividade.getId()) && Objects.equals(getNome(), atividade.getNome()) && getTipo() == atividade.getTipo() && Objects.equals(getDescricao(), atividade.getDescricao()) && Objects.equals(getData(), atividade.getData()) && Objects.equals(getHorarioInicial(), atividade.getHorarioInicial()) && Objects.equals(getHorarioFinal(), atividade.getHorarioFinal()) && Objects.equals(getLocal(), atividade.getLocal()) && Objects.equals(getEdicao(), atividade.getEdicao());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNome(), getTipo(), getDescricao(), getData(), getHorarioInicial(), getHorarioFinal(), getLocal(), getEdicao());
  }

  @Override
  public String toString() {
    return "Atividade{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", tipo=" + tipo +
        ", descricao='" + descricao + '\'' +
        ", data=" + data +
        ", horarioInicial=" + horarioInicial +
        ", horarioFinal=" + horarioFinal +
        ", local=" + local +
        ", edicao=" + edicao +
        '}';
  }
}
