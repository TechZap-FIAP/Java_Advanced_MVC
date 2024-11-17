package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindDetailedDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindRegisterDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindUpdateDTO;
import br.com.fiap.techzap.model.TurbineWind;
import br.com.fiap.techzap.model.TypeTurbine;
import br.com.fiap.techzap.repository.TurbineWindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TurbineWindService {

    private final TurbineWindRepository turbineWindRepository;
    private final TypeTurbineService typeTurbineService;

    @Autowired
    public TurbineWindService( TurbineWindRepository turbineWindRepository, TypeTurbineService typeTurbineService1){
        this.turbineWindRepository = turbineWindRepository;
        this.typeTurbineService = typeTurbineService1;
    }

    public TurbineWind findById(int id) { return turbineWindRepository.findById(id); }

    public TurbineWind create(TurbineWindRegisterDTO turbineWindRegisterDTO) {
        TurbineWind turbineWind = new TurbineWind(turbineWindRegisterDTO);
        turbineWind.setSize(turbineWind.getSize());
        turbineWind.setPrice(turbineWind.getPrice());
        turbineWind.setMaterial(turbineWind.getMaterial());
        TypeTurbine typeTurbine = typeTurbineService.findById(turbineWindRegisterDTO.idTypeTurbine());
        turbineWind.setTypeTurbine(typeTurbine);
        return turbineWindRepository.save(turbineWind);
    }

    public Page<TurbineWindDetailedDTO> list(Pageable pageable){
        return turbineWindRepository.findAll(pageable).map(TurbineWindDetailedDTO::new);
    }

    public TurbineWindDetailedDTO get (Long id){
        return new TurbineWindDetailedDTO(turbineWindRepository.findById(id).get());
    }

    public void delete(Long id){
        TurbineWind turbineWind = turbineWindRepository.findById(id).get();
        turbineWindRepository.delete(turbineWind);
    }

    public TurbineWindDetailedDTO update (Long id, TurbineWindUpdateDTO turbineWindUpdateDTO){
        TurbineWind turbineWind = turbineWindRepository.findById(id).get();
        turbineWind.updateInformation(turbineWindUpdateDTO);

        turbineWindRepository.save(turbineWind);
        return new TurbineWindDetailedDTO(turbineWind);
    }

}
