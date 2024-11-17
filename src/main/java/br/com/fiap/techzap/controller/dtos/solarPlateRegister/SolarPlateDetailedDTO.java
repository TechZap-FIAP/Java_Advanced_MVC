package br.com.fiap.techzap.controller.dtos.solarPlateRegister;

import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeDetailedDTO;
import br.com.fiap.techzap.model.SolarPlate;

public record SolarPlateDetailedDTO(

        Long idSolarPlate,
        double size,
        double price,
        String material,
        Long idSolarPlateType
) {

    public SolarPlateDetailedDTO(SolarPlate solarPlate) {
        this(solarPlate.getIdSolarPlate(), solarPlate.getSize(), solarPlate.getPrice(), solarPlate.getMaterial(),
                new SolarPlateTypeDetailedDTO(solarPlate.getSolarPlateType()).id());
    }

}
