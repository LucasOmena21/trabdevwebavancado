package org.example.sbccmsapi.dtos.responses;

import java.time.LocalDate;

public record ObterEdicoesResponse(
    Long id,
    Long eventoId,
    int numero,
    int ano,
    LocalDate dataInicial,
    LocalDate dataFinal,
    String cidade
) {
}
