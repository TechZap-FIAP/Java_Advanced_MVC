package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.solarPlateRegister.SolarPlateDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateRegister.SolarPlateRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateRegister.SolarPlateUpdateDTO;
import br.com.fiap.techzap.model.SolarPlate;
import br.com.fiap.techzap.model.SolarPlateType;
import br.com.fiap.techzap.repository.SolarPlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SolarPlateService {

    private final SolarPlateRepository solarPlateRepository;
    private final SolarPlateTypeService solarPlateTypeService;

    @Autowired
    public SolarPlateService(SolarPlateRepository SolarPlateRepository, SolarPlateTypeService solarPlateTypeService) {
        this.solarPlateRepository = SolarPlateRepository;
        this.solarPlateTypeService = solarPlateTypeService;
    }

    public SolarPlate findById(int id) { return solarPlateRepository.findById(id); }

    public SolarPlate create(SolarPlateRegisterDTO solarPlateRegisterDTO) {
        SolarPlate solarPlate = new SolarPlate(solarPlateRegisterDTO);
        solarPlate.setSize(solarPlate.getSize());
        solarPlate.setPrice(solarPlate.getPrice());
        solarPlate.setMaterial(solarPlate.getMaterial());
        SolarPlateType solarPlateType = solarPlateTypeService.findById(solarPlateRegisterDTO.idSolarPlateType());
        solarPlate.setSolarPlateType(solarPlateType);
        return solarPlateRepository.save(solarPlate);
    }

    public Page<SolarPlateDetailedDTO> list(Pageable pageable){
        return solarPlateRepository.findAll(pageable).map(SolarPlateDetailedDTO::new);
    }

    public SolarPlateDetailedDTO get (Long id){
        return new SolarPlateDetailedDTO(solarPlateRepository.findById(id).get());
    }

    public void delete(Long id){
        SolarPlate solarPlate = solarPlateRepository.findById(id).get();
        solarPlateRepository.delete(solarPlate);
    }

    public SolarPlateDetailedDTO update (Long id, SolarPlateUpdateDTO solarPlateUpdateDTO){
        SolarPlate solarPlate = solarPlateRepository.findById(id).get();
        solarPlate.updateInformation(solarPlateUpdateDTO);

        solarPlateRepository.save(solarPlate);
        return new SolarPlateDetailedDTO(solarPlate);
    }

}
