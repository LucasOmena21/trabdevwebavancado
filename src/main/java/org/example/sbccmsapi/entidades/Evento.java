package org.example.sbccmsapi.entidades;

import jakarta.persistence.*;
import org.example.sbccmsapi.dtos.requests.CriarEventoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEventosResponse;

import java.util.List;
import java.util.Objects;

@Entity(name = "eventos")
public class Evento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String sigla;
  private String descricao;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Edicao> edicoes;

  public Evento() {

  }

  public Evento(
      Long id,
      String nome,
      String sigla,
      String descricao,
      List<Edicao> edicoes
  ) {
    this.id = id;
    this.nome = nome;
    this.sigla = sigla;
    this.descricao = descricao;
    this.edicoes = edicoes;
  }

  public static Evento from(final CriarEventoRequest request) {
    return new Evento(null, request.nome(), request.sigla(), request.descricao(), null);
  }

  public ObterEventosResponse toResponse() {
    return new ObterEventosResponse(
        getId(),
        getNome(),
        getSigla(),
        getDescricao()
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

  public String getSigla() {
    return sigla;
  }

  public void setSigla(final String sigla) {
    this.sigla = sigla;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(final String descricao) {
    this.descricao = descricao;
  }

  public List<Edicao> getEdicoes() {
    return edicoes;
  }

  public void setEdicoes(final List<Edicao> edicoes) {
    this.edicoes = edicoes;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Evento evento = (Evento) o;
    return Objects.equals(getId(), evento.getId()) && Objects.equals(getNome(), evento.getNome()) && Objects.equals(getSigla(), evento.getSigla()) && Objects.equals(getDescricao(), evento.getDescricao()) && Objects.equals(getEdicoes(), evento.getEdicoes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNome(), getSigla(), getDescricao(), getEdicoes());
  }

  @Override
  public String toString() {
    return "Evento{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", sigla='" + sigla + '\'' +
        ", descricao='" + descricao + '\'' +
        ", edicoes=" + edicoes +
        '}';
  }
}
