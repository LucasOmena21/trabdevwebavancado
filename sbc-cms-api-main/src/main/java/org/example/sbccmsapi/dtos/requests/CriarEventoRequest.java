package org.example.sbccmsapi.dtos.requests;

public record CriarEventoRequest(
    String nome,
    String sigla,
    String descricao
) {
}
