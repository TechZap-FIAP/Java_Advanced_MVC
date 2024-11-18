package br.com.fiap.techzap.controller.dtos.planContracted;

import jakarta.validation.constraints.NotNull;

public record PlanContractedUpdateDTO(
        Long idUser,
        Long idSolarPlate,
        Long idTurbineWind
) {
}
