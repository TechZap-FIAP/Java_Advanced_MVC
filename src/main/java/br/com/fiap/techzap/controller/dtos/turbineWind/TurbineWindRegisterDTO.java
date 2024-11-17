package br.com.fiap.techzap.controller.dtos.turbineWind;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurbineWindRegisterDTO(

        @NotNull double size,
        @NotNull double price,
        @NotBlank String material,
        @NotNull Long idTypeTurbine

) {
}
