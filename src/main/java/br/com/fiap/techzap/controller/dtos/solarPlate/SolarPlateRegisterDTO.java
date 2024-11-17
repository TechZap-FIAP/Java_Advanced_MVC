package br.com.fiap.techzap.controller.dtos.solarPlate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolarPlateRegisterDTO(

        @NotNull double size,
        @NotNull double price,
        @NotBlank String material,
        @NotNull Long idSolarPlateType
) {
}
