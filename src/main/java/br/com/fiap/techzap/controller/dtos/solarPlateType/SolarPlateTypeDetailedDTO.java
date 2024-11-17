package br.com.fiap.techzap.controller.dtos.solarPlateType;

import br.com.fiap.techzap.model.SolarPlateType;

public record SolarPlateTypeDetailedDTO(

        Long id,
        String voltage,
        String model,
        String manufacturer,
        String cell_type,
        int product_warranty,
        double cost_per_watt

) {
    public SolarPlateTypeDetailedDTO(SolarPlateType plateType){
        this(plateType.getIdTypeSolarBoard() ,plateType.getVoltage(), plateType.getModel(), plateType.getManufacturer(),
                plateType.getCell_type(), plateType.getProduct_warranty(), plateType.getCost_per_watt());
    }
}
