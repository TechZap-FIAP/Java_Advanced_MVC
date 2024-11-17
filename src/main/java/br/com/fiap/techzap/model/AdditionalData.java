package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataRegisterDTO;
import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_ADDITIONAL_DATA")
public class AdditionalData {

    @Column(name = "id_additional_data")
    @Id
    private Long idAdditionalData;

    @Column(name = "dt_birth_date")
    private LocalDate birthDate;

    @Column(name = "ds_cpf")
    private String cpf;

    @Column(name = "ds_telephone")
    private String telephone;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    private Address address;

    public AdditionalData(AdditionalDataRegisterDTO additionalDataRegisterDTO){
        this.birthDate = additionalDataRegisterDTO.birthDate();
        this.cpf = additionalDataRegisterDTO.cpf();
        this.telephone = additionalDataRegisterDTO.telephone();
    }

    public void updateInformation(AdditionalDataUpdateDTO additionalDataUpdateDTO){
        if (additionalDataUpdateDTO.birthDate() != null){
            this.birthDate = additionalDataUpdateDTO.birthDate();
        }
        if (additionalDataUpdateDTO.cpf() != null){
            this.cpf = additionalDataUpdateDTO.cpf();
        }
        if (additionalDataUpdateDTO.telephone() != null){
            this.telephone = additionalDataUpdateDTO.telephone();
        }
    }

}
