package br.com.fiap.techzap.controller.dtos.turbineWind;

public record TurbineWindUpdateDTO(

        double size,
        double price,
        String material,
        Long idTypeTurbine

) {
}
