package br.com.fiap.techzap.controller.dtos.additionalData;

import br.com.fiap.techzap.controller.dtos.address.AddressDetailedDTO;
import br.com.fiap.techzap.model.AdditionalData;

import java.time.LocalDate;

public record AdditionalDataDetailedDTO(

        Long idAdditionalData,
        LocalDate birthDate,
        String cpf,
        String telephone,
        AddressDetailedDTO address
) {

    public AdditionalDataDetailedDTO(AdditionalData additionalData) {
        this(additionalData.getIdAdditionalData(), additionalData.getBirthDate(), additionalData.getCpf(), additionalData.getTelephone(),
                new AddressDetailedDTO(additionalData.getAddress()));
    }

}
