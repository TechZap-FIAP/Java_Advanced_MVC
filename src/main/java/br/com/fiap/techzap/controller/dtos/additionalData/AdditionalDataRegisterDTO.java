package br.com.fiap.techzap.controller.dtos.additionalData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AdditionalDataRegisterDTO(

        @NotBlank LocalDate birthDate,
        @NotBlank String cpf,
        @NotBlank String telephone,
        @NotNull Long idAddress

) {
}
