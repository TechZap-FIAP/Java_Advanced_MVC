package br.com.fiap.techzap.controller.dtos.typeTurbine;

import br.com.fiap.techzap.model.TypeTurbine;

public record TypeTurbineDetailedDTO (

        Long id,
        String voltage,
        String model,
        String manufacturer,
        String generator_type,
        int warranty_years

){
    public TypeTurbineDetailedDTO(TypeTurbine typeTurbine) {
        this(typeTurbine.getIdTypeTurbine() ,typeTurbine.getGenerator_type(), typeTurbine.getVoltage(), typeTurbine.getModel(),
                typeTurbine.getManufacturer(), typeTurbine.getWarranty_years());
    }
}
