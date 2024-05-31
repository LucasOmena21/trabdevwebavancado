package org.example.sbccmsapi.dtos.requests;

import java.time.LocalDate;

public record CriarEdicaoRequest(
    int numero,
    int ano,
    LocalDate dataInicial,
    LocalDate dataFinal,
    String cidade
) {

}
