package br.com.fiap.techzap.controller.dtos.solarPlateType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolarPlateTypeRegisterDTO(

        @NotBlank String voltage,
        @NotBlank String model,
        @NotBlank String manufacturer,
        @NotBlank String cell_type,
        @NotNull int product_warranty,
        @NotNull double cost_per_watt

        ) {
}
