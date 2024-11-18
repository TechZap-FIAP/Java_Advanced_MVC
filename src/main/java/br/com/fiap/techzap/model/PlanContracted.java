package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedRegisterDTO;
import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_CONTRACTED_PLAN")
public class PlanContracted {

    @Column(name = "id_contracted_plan")
    @Id
    private Long idPlanContracted;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solar_board")
    private SolarPlate solarPlate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turbine_wind")
    private TurbineWind turbineWind;

    public PlanContracted(PlanContractedRegisterDTO planContractedRegisterDTO) {

    }

    public void updateInformation(PlanContractedUpdateDTO planContractedUpdateDTO) {

    }
}
