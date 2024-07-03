package org.example.sbccmsapi.dtos.responses;

import java.util.List;

public record ObterEspacoResponse(
    Long id,
    String nome,
    String localizacao,
    int capacidade,
    List<String> recursos
) {
}
