package org.example.sbccmsapi.entidades;

import jakarta.persistence.*;
import org.example.sbccmsapi.dtos.requests.CriarUsuarioRequest;
import org.example.sbccmsapi.dtos.responses.ObterUsuariosResponse;

import java.util.List;

@Entity(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String login;
  private String email;
  private String nome;
  private String afiliacao;
  private boolean admin = false;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  private List<Atividade> favoritos;

  public Usuario() {
  }

  public Usuario(
      Long id,
      String login,
      String email,
      String nome,
      String afiliacao,
      boolean admin,
      List<Atividade> favoritos
  ) {
    this.id = id;
    this.login = login;
    this.email = email;
    this.nome = nome;
    this.admin = admin;
    this.afiliacao = afiliacao;
    this.favoritos = favoritos;
  }

  public static Usuario from(CriarUsuarioRequest request) {
    return new Usuario(
        null,
        request.login(),
        request.email(),
        request.nome(),
        request.afiliacao(),
        false,
        null
    );
  }

  public static ObterUsuariosResponse toResponse(Usuario usuario) {
    return new ObterUsuariosResponse(
        usuario.getId(),
        usuario.getNome(),
        usuario.getEmail(),
        usuario.getAfiliacao()
    );
  }

  public void favoritar(Atividade atividade) {
    this.favoritos.add(atividade);
  }

  public void desfavoritar(Atividade atividade) {
    this.favoritos.remove(atividade);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAfiliacao() {
    return afiliacao;
  }

  public void setAfiliacao(String afiliacao) {
    this.afiliacao = afiliacao;
  }

  public List<Atividade> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(List<Atividade> favoritos) {
    this.favoritos = favoritos;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
