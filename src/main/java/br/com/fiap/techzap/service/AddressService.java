package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.address.AddressDetailedDTO;
import br.com.fiap.techzap.controller.dtos.address.AddressRegisterDTO;
import br.com.fiap.techzap.controller.dtos.address.AddressUpdateDTO;
import br.com.fiap.techzap.model.Address;
import br.com.fiap.techzap.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) { this.addressRepository = addressRepository; }

    public Address findById(Long idAddress) {
        return addressRepository.findById(idAddress).orElse(null);
    }

    public Address create(AddressRegisterDTO addressRegisterDTO) {
        Address address = new Address(addressRegisterDTO);
        address.setStreet(address.getStreet());
        address.setCity(address.getCity());
        address.setState(address.getState());
        address.setNumber(address.getNumber());
        address.setNeighborhood(address.getNeighborhood());
        return addressRepository.save(address);
    }

    public Page<AddressDetailedDTO>list(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressDetailedDTO::new);
    }

    public AddressDetailedDTO get(Long id) {
        return new AddressDetailedDTO(addressRepository.findById(id).get());
    }

    public void delete(Long id) {
        Address address = addressRepository.findById(id).get();
        addressRepository.delete(address);
    }

    public AddressDetailedDTO update(Long id, AddressUpdateDTO addressUpdateDTO) {
        Address address = addressRepository.findById(id).get();
        address.updateInformation(addressUpdateDTO);

        addressRepository.save(address);
        return new AddressDetailedDTO(address);
    }

}
