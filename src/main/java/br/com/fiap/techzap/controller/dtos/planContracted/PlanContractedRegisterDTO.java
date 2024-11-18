package br.com.fiap.techzap.controller.dtos.planContracted;

import jakarta.validation.constraints.NotNull;

public record PlanContractedRegisterDTO(

        @NotNull Long idUser,
        @NotNull Long idSolarPlate,
        @NotNull Long idTurbineWind

) {
}
