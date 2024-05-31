package org.example.sbccmsapi.dtos.responses;

public record ObterEventosResponse(
    Long id,
    String nome,
    String sigla,
    String descricao
) {
}
