package br.com.fiap.techzap.controller.dtos.solarPlateRegister;

public record SolarPlateUpdateDTO(

        double size,
        double price,
        String material,
        Long idSolarPlateType

) {
}
