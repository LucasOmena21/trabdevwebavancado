package org.example.sbccmsapi.dtos.requests;

import java.util.List;

public record CriarEspacoRequest(
    String nome,
    String localizacao,
    int capacidade,
    List<String> recursos
) {
}
