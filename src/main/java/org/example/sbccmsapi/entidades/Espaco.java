package org.example.sbccmsapi.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.sbccmsapi.dtos.requests.CriarEspacoRequest;
import org.example.sbccmsapi.dtos.responses.ObterEspacoResponse;

import java.util.List;
import java.util.Objects;

@Entity(name = "espacos")
public class Espaco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String localizacao;
  private int capacidade;
  private List<String> recursos;

  public Espaco() {
  }

  public Espaco(
      Long id,
      String nome,
      String localizacao,
      int capacidade,
      List<String> recursos
  ) {
    this.id = id;
    this.nome = nome;
    this.localizacao = localizacao;
    this.capacidade = capacidade;
    this.recursos = recursos;
  }

  public static Espaco from(CriarEspacoRequest request) {
    return new Espaco(
        null,
        request.nome(),
        request.localizacao(),
        request.capacidade(),
        request.recursos()
    );
  }

  public ObterEspacoResponse toResponse() {
    return new ObterEspacoResponse(
        getId(),
        getNome(),
        getLocalizacao(),
        getCapacidade(),
        getRecursos()
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

  public String getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(final String localizacao) {
    this.localizacao = localizacao;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(final int capacidade) {
    this.capacidade = capacidade;
  }

  public List<String> getRecursos() {
    return recursos;
  }

  public void setRecursos(final List<String> recursos) {
    this.recursos = recursos;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Espaco espaco = (Espaco) o;
    return getCapacidade() == espaco.getCapacidade() && Objects.equals(getId(), espaco.getId()) && Objects.equals(getNome(), espaco.getNome()) && Objects.equals(getLocalizacao(), espaco.getLocalizacao()) && Objects.equals(getRecursos(), espaco.getRecursos());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNome(), getLocalizacao(), getCapacidade(), getRecursos());
  }

  @Override
  public String toString() {
    return "Espaco{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", localizacao='" + localizacao + '\'' +
        ", capacidade=" + capacidade +
        ", recursos=" + recursos +
        '}';
  }
}
