package org.example.sbccmsapi.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CriarUsuarioRequest(
    @JsonProperty("login")
    @NotNull
    @Length(min = 3, max = 100)
    String login,

    @JsonProperty("email")
    @NotNull
    @Length(min = 3, max = 100)
    @Email
    String email,

    @JsonProperty("nome")
    @NotNull
    @Length(min = 3, max = 100)
    String nome,

    @JsonProperty("afiliacao")
    @NotNull
    @Length(min = 3, max = 100)
    String afiliacao
) {
}
