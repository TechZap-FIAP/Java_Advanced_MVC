package br.com.fiap.techzap.controller.dtos.solarPlateType;

public record SolarPlateTypeUpdateDTO(

        String voltage,
        String model,
        String manufacturer,
        String cell_type,
        int product_warranty,
        double cost_per_watt

) {
}
