package br.com.fiap.techzap.controller.dtos.solarPlate;

public record SolarPlateUpdateDTO(

        double size,
        double price,
        String material,
        Long idSolarPlateType

) {
}
