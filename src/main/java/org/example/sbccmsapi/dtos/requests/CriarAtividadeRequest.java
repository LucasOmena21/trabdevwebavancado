package org.example.sbccmsapi.dtos.requests;

import org.example.sbccmsapi.enums.TipoAtividade;

import java.time.LocalDate;
import java.time.LocalTime;

public record CriarAtividadeRequest(
    String nome,
    TipoAtividade tipo,
    String descricao,
    LocalDate data,
    LocalTime horarioInicial,
    LocalTime horarioFinal,
    Long localId
) {
}
