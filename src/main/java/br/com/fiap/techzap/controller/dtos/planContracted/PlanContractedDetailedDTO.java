package br.com.fiap.techzap.controller.dtos.planContracted;

import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateDetailedDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindDetailedDTO;
import br.com.fiap.techzap.controller.dtos.user.UserDetailedDTO;
import br.com.fiap.techzap.model.PlanContracted;

public record PlanContractedDetailedDTO(

        Long id,
        Long idUser,
        Long idSolarPlate,
        Long idTurbineWind

) {

    public PlanContractedDetailedDTO(PlanContracted planContracted) {
        this(planContracted.getIdPlanContracted(),
                new UserDetailedDTO(planContracted.getUser()).idUser(),
                new SolarPlateDetailedDTO(planContracted.getSolarPlate()).idSolarPlate(),
                new TurbineWindDetailedDTO(planContracted.getTurbineWind()).idTurbineWind());
    }

}
