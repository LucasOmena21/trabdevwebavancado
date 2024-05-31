package org.example.sbccmsapi.dtos.responses;

public record ObterUsuariosResponse(
    Long id,
    String nome,
    String email
) {
}
