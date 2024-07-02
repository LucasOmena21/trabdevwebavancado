package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CriarEspacoRequest(
    @JsonProperty("nome")
    @NotNull
    @Length(min = 3, max = 100)
    String nome,

    @JsonProperty("localizacao")
    @NotNull
    @Length(min = 3, max = 100)
    String localizacao,

    @JsonProperty("capacidade")
    @NotNull
    @Min(1)
    int capacidade,

    @JsonProperty("recursos")
    @NotNull
    @Length(min = 1)
    List<String> recursos
) {
}
