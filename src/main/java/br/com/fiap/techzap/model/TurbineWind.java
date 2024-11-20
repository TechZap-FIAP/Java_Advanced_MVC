package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindRegisterDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_WIND_TURBINE")
public class TurbineWind {

    @Column(name = "id_turbine_wind")
    @Id
    private Long idTurbinaWind ;

    @Column(name = "ds_material")
    private String material;

    @Column(name = "vl_size")
    private double size ;

    @Column(name = "vl_price")
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_turbine")
    private TypeTurbine typeTurbine;

    public TurbineWind(TurbineWindRegisterDTO turbineWindRegisterDTO){
        this.price = turbineWindRegisterDTO.price();
        this.material = turbineWindRegisterDTO.material();
        this.size = turbineWindRegisterDTO.size() ;
    }

    public void updateInformation(TurbineWindUpdateDTO turbineWindUpdateDTO){
        if (turbineWindUpdateDTO.material() != null){
            this.material = turbineWindUpdateDTO.material();
        }
        if (turbineWindUpdateDTO.price() != 0){
            this.price = turbineWindUpdateDTO.price();
        }
        if (turbineWindUpdateDTO.size() != 0){
            this.size = turbineWindUpdateDTO.size() ;
        }
    }

}
