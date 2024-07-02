package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.sbccmsapi.validators.ValidTimeRange;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@ValidTimeRange
public record CriarEdicaoRequest(
    @JsonProperty("numero")
    @NotNull
    @Min(1)
    int numero,

    @JsonProperty("ano")
    @NotNull
    @Min(1970)
    int ano,

    @JsonProperty("data_inicial")
    @Schema(type = "string", format = "date")
    @NotNull
    LocalDate dataInicial,

    @JsonProperty("data_final")
    @Schema(type = "string", format = "date")
    @NotNull
    LocalDate dataFinal,

    @JsonProperty("cidade")
    @NotNull
    @Length(min = 3, max = 100)
    String cidade
) {

}
