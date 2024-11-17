package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataDetailedDTO;
import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataRegisterDTO;
import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataUpdateDTO;
import br.com.fiap.techzap.model.AdditionalData;
import br.com.fiap.techzap.model.Address;
import br.com.fiap.techzap.repository.AdditionalDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdditionalDataService {

    private final AdditionalDataRepository additionalDataRepository;
    private final AddressService addressService;

    public AdditionalDataService(AdditionalDataRepository additionalDataRepository, AddressService addressService) {
        this.additionalDataRepository = additionalDataRepository;
        this.addressService = addressService;
    }

    public AdditionalData findById(int id) { return additionalDataRepository.findById(id); }

    public AdditionalData create(AdditionalDataRegisterDTO additionalDataRegisterDTO) {
        AdditionalData additionalData = new AdditionalData(additionalDataRegisterDTO);
        additionalData.setCpf(additionalData.getCpf());
        additionalData.setTelephone(additionalData.getTelephone());
        additionalData.setBirthDate(additionalData.getBirthDate());
        Address address = addressService.findById(additionalDataRegisterDTO.idAddress());
        additionalData.setAddress(address);
        return additionalDataRepository.save(additionalData);
    }

    public Page<AdditionalDataDetailedDTO> list (Pageable pageable) {
        return additionalDataRepository.findAll(pageable).map(AdditionalDataDetailedDTO::new);
    }

    public AdditionalDataDetailedDTO get (Long id){
        return new AdditionalDataDetailedDTO(additionalDataRepository.findById(id).get());
    }

    public void delete (Long id) {
        AdditionalData additionalData = additionalDataRepository.findById(id).get();
        additionalDataRepository.delete(additionalData);
    }

    public AdditionalDataDetailedDTO update (Long id, AdditionalDataUpdateDTO additionalDataUpdateDTO) {
        AdditionalData additionalData = additionalDataRepository.findById(id).get();
        additionalData.updateInformation(additionalDataUpdateDTO);

        additionalDataRepository.save(additionalData);
        return new AdditionalDataDetailedDTO(additionalData);
    }

}
