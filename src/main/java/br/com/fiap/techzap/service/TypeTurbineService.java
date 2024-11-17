package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineDetailedDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineRegisterDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineUpdateDTO;
import br.com.fiap.techzap.model.TypeTurbine;
import br.com.fiap.techzap.repository.TypeTurbineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeTurbineService {

    private final TypeTurbineRepository typeTurbineRepository;

    public TypeTurbineService(TypeTurbineRepository typeTurbineRepository) { this.typeTurbineRepository = typeTurbineRepository; }

    public TypeTurbine findById(Long id) { return typeTurbineRepository.findById(id).get(); }

    public TypeTurbine create(TypeTurbineRegisterDTO typeTurbineRegisterDTO){
        TypeTurbine typeTurbine = new TypeTurbine(typeTurbineRegisterDTO);
        typeTurbine.setVoltage(typeTurbine.getVoltage());
        typeTurbine.setGenerator_type(typeTurbine.getGenerator_type());
        typeTurbine.setModel(typeTurbine.getModel());
        typeTurbine.setManufacturer(typeTurbine.getManufacturer());
        typeTurbine.setWarranty_years(typeTurbine.getWarranty_years());
        return typeTurbineRepository.save(typeTurbine);
    }

    public Page<TypeTurbineDetailedDTO> list(Pageable pageable){
        return typeTurbineRepository.findAll(pageable).map(TypeTurbineDetailedDTO::new);
    }

    public TypeTurbineDetailedDTO get (Long id){
        return new TypeTurbineDetailedDTO(typeTurbineRepository.findById(id).get());
    }

    public void delete(Long id){
        TypeTurbine typeTurbine = typeTurbineRepository.findById(id).get();
        typeTurbineRepository.delete(typeTurbine);
    }

    public TypeTurbineDetailedDTO update(Long id, TypeTurbineUpdateDTO typeTurbineUpdateDTO){
        TypeTurbine typeTurbine = typeTurbineRepository.findById(id).get();
        typeTurbine.updateInformation(typeTurbineUpdateDTO);

        typeTurbineRepository.save(typeTurbine);
        return new TypeTurbineDetailedDTO(typeTurbine);
    }

}
