package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.solarPlateRegister.SolarPlateRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateRegister.SolarPlateUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_SOLAR_BOARD")
public class SolarPlate {

    @Column(name = "id_solar_board")
    @Id
    private Long idSolarPlate;

    @Column(name = "ds_material")
    private String material;

    @Column(name = "vl_siza")
    private double size ;

    @Column(name = "vl_price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solar_plate_type")
    private SolarPlateType solarPlateType;

    public SolarPlate(SolarPlateRegisterDTO solarPlateRegisterDTO){
        this.price = solarPlateRegisterDTO.price();
        this.material = solarPlateRegisterDTO.material();
        this.size = solarPlateRegisterDTO.size() ;
    }

    public void updateInformation(SolarPlateUpdateDTO solarPlateUpdateDTO){
        if (solarPlateUpdateDTO.material() != null){
            this.material = solarPlateUpdateDTO.material();
        }
        if (solarPlateUpdateDTO.price() != 0){
            this.price = solarPlateUpdateDTO.price();
        }
        if (solarPlateUpdateDTO.size() != 0){
            this.size = solarPlateUpdateDTO.size() ;
        }
    }

}
