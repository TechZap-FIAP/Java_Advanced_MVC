package br.com.fiap.techzap.controller.dtos.address;

public record AddressUpdateDTO(

        String street,
        String number,
        String neighborhood,
        String city,
        String state

) {
}
