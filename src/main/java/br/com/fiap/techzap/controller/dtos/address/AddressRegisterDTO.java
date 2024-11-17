package br.com.fiap.techzap.controller.dtos.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRegisterDTO(

        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String neighborhood,
        @NotBlank String city,
        @NotBlank String state

) {
}
