package br.com.fiap.techzap.controller.dtos.additionalData;

import java.time.LocalDate;

public record AdditionalDataUpdateDTO(

        LocalDate birthDate,
        String cpf,
        String telephone

) {
}
