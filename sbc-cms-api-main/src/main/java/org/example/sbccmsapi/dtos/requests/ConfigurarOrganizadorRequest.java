package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ConfigurarOrganizadorRequest(
    @JsonProperty("organizador_id")
    @NotNull
    Long organizadorId
) {
}
