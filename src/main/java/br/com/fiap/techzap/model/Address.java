package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.address.AddressRegisterDTO;
import br.com.fiap.techzap.controller.dtos.address.AddressUpdateDTO;
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
@Table(name = "T_TZ_ADDRESS")
public class Address {

    @Column(name = "id_address")
    @Id
    private Long idAddress;

    @Column(name = "ds_street")
    private String street;

    @Column(name = "ds_number")
    private String number;

    @Column(name = "ds_neighborhood")
    private String neighborhood;

    @Column(name = "ds_city ")
    private String city ;

    @Column(name = "ds_state")
    private String state;

    public Address(AddressRegisterDTO addressRegisterDTO){
        this.street = addressRegisterDTO.street();
        this.number = addressRegisterDTO.number();
        this.neighborhood = addressRegisterDTO.neighborhood();
        this.city = addressRegisterDTO.city();
        this.state = addressRegisterDTO.state();
    }

    public void updateInformation(AddressUpdateDTO addressUpdateDTO){
        if (addressUpdateDTO.street() != null){ this.street = addressUpdateDTO.street(); }
        if (addressUpdateDTO.number() != null){ this.number = addressUpdateDTO.number(); }
        if (addressUpdateDTO.neighborhood() != null){ this.neighborhood = addressUpdateDTO.neighborhood(); }
        if (addressUpdateDTO.city() != null){ this.city = addressUpdateDTO.city(); }
        if (addressUpdateDTO.state() != null){ this.state = addressUpdateDTO.state(); }
    }

}
