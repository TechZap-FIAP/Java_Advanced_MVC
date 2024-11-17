package br.com.fiap.techzap.controller.dtos.typeTurbine;

public record TypeTurbineUpdateDTO(

        String voltage,
        String model,
        String manufacturer,
        String generator_type,
        int warranty_years

) {
}
