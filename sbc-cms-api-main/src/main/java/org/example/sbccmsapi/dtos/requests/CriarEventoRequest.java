package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CriarEventoRequest(
    @JsonProperty("nome")
    @NotNull
    @Length(min = 3, max = 100)
    String nome,

    @JsonProperty("sigla")
    @NotNull
    @Length(min = 1, max = 10)
    String sigla,

    @JsonProperty("descricao")
    @NotNull
    @Length(min = 5, max = 1000)
    String descricao
) {
}
