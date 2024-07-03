package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, max = 10)
    List<String> recursos
) {
}
