package br.com.fiap.techzap.controller.dtos.typeTurbine;

import jakarta.validation.constraints.NotBlank;

public record TypeTurbineRegisterDTO(

        @NotBlank String voltage,
        @NotBlank String model,
        @NotBlank String manufacturer,
        @NotBlank String generator_type,
        @NotBlank int warranty_years

) {
}
