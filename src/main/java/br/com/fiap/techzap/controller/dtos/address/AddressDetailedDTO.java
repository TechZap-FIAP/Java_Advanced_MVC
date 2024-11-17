package br.com.fiap.techzap.controller.dtos.address;

import br.com.fiap.techzap.model.Address;

public record AddressDetailedDTO(

        String street,
        String number,
        String neighborhood,
        String city,
        String state
) {
    public AddressDetailedDTO(Address address) {
        this(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState());
    }
}
