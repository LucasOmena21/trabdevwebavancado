package org.example.sbccmsapi.dtos.responses;

import org.example.sbccmsapi.entidades.Espaco;
import org.example.sbccmsapi.enums.TipoAtividade;

import java.time.LocalDate;
import java.time.LocalTime;

public record ObterAtividadeResponse(
    Long id,
    String nome,
    TipoAtividade tipo,
    String descricao,
    LocalDate data,
    LocalTime horarioInicial,
    LocalTime horarioFinal,
    Espaco local
) {
}
