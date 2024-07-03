package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.example.sbccmsapi.enums.TipoAtividade;
import org.example.sbccmsapi.validators.IsTodayOrAfter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

public record CriarAtividadeRequest(
    @JsonProperty("nome")
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 3, max = 100)
    String nome,

    @JsonProperty("tipo")
    @NotNull
    TipoAtividade tipo,

    @JsonProperty("descricao")
    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 5, max = 1000)
    String descricao,

    @JsonProperty("data")
    @Schema(type = "string", format = "date")
    @IsTodayOrAfter
    @NotNull
    LocalDate data,

    @JsonProperty("horario_inicial")
    @Schema(type = "string", format = "time", defaultValue = "00:00:00")
    @NotNull
    LocalTime horarioInicial,

    @JsonProperty("horario_final")
    @Schema(type = "string", format = "time", defaultValue = "23:59:59")
    @NotNull
    LocalTime horarioFinal,

    @JsonProperty("local_id")
    @NotNull
    @Min(1)
    Long localId
) {
}
