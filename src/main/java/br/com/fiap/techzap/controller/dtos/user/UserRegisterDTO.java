package br.com.fiap.techzap.controller.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(

        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String email,
        @NotBlank String password

){
}
