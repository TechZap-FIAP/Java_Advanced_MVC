package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeUpdateDTO;
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
@Table(name = "T_TZ_SOLAR_PANEL_TYPE")
public class SolarPlateType {

    @Column(name = "id_solar_plate_type")
    @Id
    private Long idTypeSolarBoard;

    @Column(name = "ds_voltage")
    private String voltage;

    @Column(name = "ds_model")
    private String model;

    @Column(name = "ds_manufacturer")
    private String manufacturer;

    @Column(name = "ds_cell_type")
    private String cell_type;

    @Column(name = "nr_product_warranty")
    private int product_warranty;

    @Column(name = "vl_cost_per_watt")
    private double cost_per_watt;

    public SolarPlateType(SolarPlateTypeRegisterDTO solarPlateTypeRegisterDTO){
        this.voltage = solarPlateTypeRegisterDTO.voltage();
        this.model = solarPlateTypeRegisterDTO.model();
        this.manufacturer = solarPlateTypeRegisterDTO.manufacturer();
        this.cell_type = solarPlateTypeRegisterDTO.cell_type();
        this.product_warranty = solarPlateTypeRegisterDTO.product_warranty();
        this.cost_per_watt = solarPlateTypeRegisterDTO.cost_per_watt();
    }

    public void updateInformation(SolarPlateTypeUpdateDTO solarPlateTypeUpdateDTO){
        if (solarPlateTypeUpdateDTO.voltage() != null){this.voltage = solarPlateTypeUpdateDTO.voltage();}
        if (solarPlateTypeUpdateDTO.model() != null){this.model = solarPlateTypeUpdateDTO.model();}
        if (solarPlateTypeUpdateDTO.manufacturer() != null){this.manufacturer = solarPlateTypeUpdateDTO.manufacturer();}
        if (solarPlateTypeUpdateDTO.cell_type() != null){this.cell_type = solarPlateTypeUpdateDTO.cell_type();}
        if (solarPlateTypeUpdateDTO.product_warranty() != 0){this.product_warranty = solarPlateTypeUpdateDTO.product_warranty();}
        if (solarPlateTypeUpdateDTO.cost_per_watt() != 0){this.cost_per_watt = solarPlateTypeUpdateDTO.cost_per_watt();}
    }

}
