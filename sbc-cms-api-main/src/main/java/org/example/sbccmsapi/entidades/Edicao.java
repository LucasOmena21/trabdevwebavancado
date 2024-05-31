package org.example.sbccmsapi.entidades;

import jakarta.persistence.*;
import org.example.sbccmsapi.dtos.requests.CriarEdicaoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEdicoesResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity(name = "edicoes")
public class Edicao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int numero;
  private int ano;
  @Column(name = "data_inicial")
  private LocalDate dataInicial;
  @Column(name = "data_final")
  private LocalDate dataFinal;
  private String cidade;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Atividade> atividades;
  @ManyToOne
  private Evento evento;
  @OneToOne()
  @JoinColumn
  private Usuario organizador;

  public Edicao() {
  }

  public Edicao(
      Long id,
      int numero,
      int ano,
      LocalDate dataInicial,
      LocalDate dataFinal,
      String cidade,
      List<Atividade> atividades,
      Evento evento
  ) {
    this.id = id;
    this.numero = numero;
    this.ano = ano;
    this.dataInicial = dataInicial;
    this.dataFinal = dataFinal;
    this.cidade = cidade;
    this.atividades = atividades;
    this.evento = evento;
  }

  public static Edicao from(final CriarEdicaoRequest request, final Evento evento) {
    return new Edicao(null, request.numero(), request.ano(), request.dataInicial(), request.dataFinal(), request.cidade(), null, evento);
  }

  public ObterEdicoesResponse toResponse() {
    return new ObterEdicoesResponse(
        getId(),
        getEvento().getId(),
        getNumero(),
        getAno(),
        getDataInicial(),
        getDataFinal(),
        getCidade()
    );
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public LocalDate getDataInicial() {
    return dataInicial;
  }

  public void setDataInicial(LocalDate dataInicial) {
    this.dataInicial = dataInicial;
  }

  public LocalDate getDataFinal() {
    return dataFinal;
  }

  public void setDataFinal(LocalDate dataFinal) {
    this.dataFinal = dataFinal;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public List<Atividade> getAtividades() {
    return atividades;
  }

  public void setAtividades(List<Atividade> atividades) {
    this.atividades = atividades;
  }

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }

  public Usuario getOrganizador() {
    return organizador;
  }

  public void setOrganizador(Usuario organizador) {
    this.organizador = organizador;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Edicao edicao = (Edicao) o;
    return getNumero() == edicao.getNumero() && getAno() == edicao.getAno() && Objects.equals(getId(), edicao.getId()) && Objects.equals(getDataInicial(), edicao.getDataInicial()) && Objects.equals(getDataFinal(), edicao.getDataFinal()) && Objects.equals(getCidade(), edicao.getCidade()) && Objects.equals(getAtividades(), edicao.getAtividades()) && Objects.equals(getEvento(), edicao.getEvento());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNumero(), getAno(), getDataInicial(), getDataFinal(), getCidade(), getAtividades(), getEvento());
  }

  @Override
  public String toString() {
    return "Edicao{" +
        "id=" + id +
        ", numero=" + numero +
        ", ano=" + ano +
        ", dataInicial=" + dataInicial +
        ", dataFinal=" + dataFinal +
        ", cidade='" + cidade + '\'' +
        ", atividades=" + atividades +
        ", evento=" + evento +
        '}';
  }
}
