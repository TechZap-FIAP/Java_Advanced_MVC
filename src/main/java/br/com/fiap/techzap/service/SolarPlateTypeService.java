package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeUpdateDTO;
import br.com.fiap.techzap.model.SolarPlateType;
import br.com.fiap.techzap.repository.SolarPlateTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SolarPlateTypeService {

    private final SolarPlateTypeRepository solarPlateTypeRepository;

    public SolarPlateTypeService(SolarPlateTypeRepository solarPlateTypeRepository) { this.solarPlateTypeRepository = solarPlateTypeRepository; }

    public SolarPlateType findById(Long id) { return solarPlateTypeRepository.findById(id).get(); }

    public SolarPlateType create(SolarPlateTypeRegisterDTO solarPlateTypeRegisterDTO){
        SolarPlateType solarPlateType = new SolarPlateType(solarPlateTypeRegisterDTO);
        solarPlateType.setVoltage(solarPlateType.getVoltage());
        solarPlateType.setCell_type(solarPlateType.getCell_type());
        solarPlateType.setModel(solarPlateType.getModel());
        solarPlateType.setManufacturer(solarPlateType.getManufacturer());
        solarPlateType.setCost_per_watt(solarPlateType.getCost_per_watt());
        solarPlateType.setProduct_warranty(solarPlateType.getProduct_warranty());
        return solarPlateTypeRepository.save(solarPlateType);
    }

    public Page<SolarPlateTypeDetailedDTO>list(Pageable pageable){
        return solarPlateTypeRepository.findAll(pageable).map(SolarPlateTypeDetailedDTO::new);
    }

    public SolarPlateTypeDetailedDTO get (Long id){
        return new SolarPlateTypeDetailedDTO(solarPlateTypeRepository.findById(id).get());
    }

    public void delete(Long id){
        SolarPlateType solarPlateType = solarPlateTypeRepository.findById(id).get();
        solarPlateTypeRepository.delete(solarPlateType);
    }

    public SolarPlateTypeDetailedDTO update(Long id, SolarPlateTypeUpdateDTO solarPlateTypeUpdateDTO){
        SolarPlateType solarPlateType = solarPlateTypeRepository.findById(id).get();
        solarPlateType.updateInformation(solarPlateTypeUpdateDTO);

        solarPlateTypeRepository.save(solarPlateType);
        return new SolarPlateTypeDetailedDTO(solarPlateType);
    }

}
