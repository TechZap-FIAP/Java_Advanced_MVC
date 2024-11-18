package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedDetailedDTO;
import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedRegisterDTO;
import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedUpdateDTO;
import br.com.fiap.techzap.model.*;
import br.com.fiap.techzap.repository.PlanContractedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanContractedService {

    private final PlanContractedRepository planContractedRepository;
    private final UserService userService;
    private final SolarPlateService solarPlateService;
    private final TurbineWindService turbineWindService;

    public PlanContractedService(PlanContractedRepository planContractedRepository, UserService userService, SolarPlateService solarPlateService, TurbineWindService turbineWindService) {
        this.planContractedRepository = planContractedRepository;
        this.userService = userService;
        this.solarPlateService = solarPlateService;
        this.turbineWindService = turbineWindService;
    }

    public PlanContracted findById(int id) { return planContractedRepository.findById(id); }

    public PlanContracted create(PlanContractedRegisterDTO planContractedRegisterDTO) {
        User user = userService.findById(Math.toIntExact(planContractedRegisterDTO.idUser()));
        SolarPlate solarPlate = solarPlateService.findById(Math.toIntExact(planContractedRegisterDTO.idSolarPlate()));
        TurbineWind turbineWind = turbineWindService.findById(Math.toIntExact(planContractedRegisterDTO.idTurbineWind()));

        if (user == null || solarPlate == null || turbineWind == null) {
            throw new RuntimeException("User, SolarPlate, or TurbineWind not found");
        }

        PlanContracted planContracted = new PlanContracted(planContractedRegisterDTO);
        planContracted.setUser(user);
        planContracted.setSolarPlate(solarPlate);
        planContracted.setTurbineWind(turbineWind);

        return planContractedRepository.save(planContracted);
    }

    public Page<PlanContractedDetailedDTO> list(Pageable pageable){
        return planContractedRepository.findAll(pageable).map(PlanContractedDetailedDTO::new);
    }

    public PlanContractedDetailedDTO get (Long id){
        return new PlanContractedDetailedDTO(planContractedRepository.findById(id).get());
    }

    public void delete(Long id){
        PlanContracted planContracted = planContractedRepository.findById(id).get();
        planContractedRepository.delete(planContracted);
    }

    public PlanContractedDetailedDTO update (Long id, PlanContractedUpdateDTO planContractedUpdateDTO){
        PlanContracted planContracted = planContractedRepository.findById(id).get();
        planContracted.updateInformation(planContractedUpdateDTO);

        planContractedRepository.save(planContracted);
        return new PlanContractedDetailedDTO(planContracted);
    }

}
