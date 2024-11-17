package br.com.fiap.techzap.controller.dtos.user;

import java.time.LocalDate;

public record UserUpdateDTO (
        String name,
        String surname,
        String email,
        String password,
        Long idAdditionalData
){
}
