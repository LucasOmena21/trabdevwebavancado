package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record FavoritarRequest(
    @JsonProperty("atividade_id")
    @NotNull
    Long atividadeId
) {
}
