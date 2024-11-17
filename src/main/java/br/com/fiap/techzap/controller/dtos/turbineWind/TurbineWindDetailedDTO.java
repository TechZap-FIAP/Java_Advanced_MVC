package br.com.fiap.techzap.controller.dtos.turbineWind;

import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineDetailedDTO;
import br.com.fiap.techzap.model.TurbineWind;

public record TurbineWindDetailedDTO(

        Long idTurbineWind,
        double size,
        double price,
        String material,
        Long idTypeTurbine
) {

    public TurbineWindDetailedDTO(TurbineWind turbineWind) {
        this(turbineWind.getIdTurbinaWind(), turbineWind.getSize(), turbineWind.getPrice(), turbineWind.getMaterial(),
                new TypeTurbineDetailedDTO(turbineWind.getTypeTurbine()).id());
    }

}