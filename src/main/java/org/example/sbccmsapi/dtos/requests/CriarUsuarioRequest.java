package org.example.sbccmsapi.dtos.requests;

public record CriarUsuarioRequest(
    String login,
    String email,
    String nome,
    String afiliacao
) {
}
