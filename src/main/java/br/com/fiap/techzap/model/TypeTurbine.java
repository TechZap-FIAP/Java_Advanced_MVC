package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeUpdateDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineRegisterDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_WIND_TURBINE_TYPE")
public class TypeTurbine {

    @Column(name = "id_type_turbine ")
    @Id
    private Long idTypeTurbine;

    @Column(name = "ds_voltage")
    private String voltage;

    @Column(name = "ds_model")
    private String model;

    @Column(name = "ds_manufacturer")
    private String manufacturer;

    @Column(name = "ds_generator_type")
    private String generator_type;

    @Column(name = "nr_warranty_years")
    private int warranty_years;

    public TypeTurbine(TypeTurbineRegisterDTO typeTurbineRegisterDTO){
        this.voltage = typeTurbineRegisterDTO.voltage();
        this.model = typeTurbineRegisterDTO.model();
        this.manufacturer = typeTurbineRegisterDTO.manufacturer();
        this.generator_type = typeTurbineRegisterDTO.generator_type();
        this.warranty_years = typeTurbineRegisterDTO.warranty_years();
    }

    public void updateInformation(TypeTurbineUpdateDTO typeTurbineUpdateDTO){
        if (typeTurbineUpdateDTO.voltage() != null){this.voltage = typeTurbineUpdateDTO.voltage();}
        if (typeTurbineUpdateDTO.model() != null){this.model = typeTurbineUpdateDTO.model();}
        if (typeTurbineUpdateDTO.manufacturer() != null){this.manufacturer = typeTurbineUpdateDTO.manufacturer();}
        if (typeTurbineUpdateDTO.generator_type() != null){this.generator_type = typeTurbineUpdateDTO.generator_type();}
        if (typeTurbineUpdateDTO.warranty_years() != 0){this.warranty_years = typeTurbineUpdateDTO.warranty_years();}
    }

}
