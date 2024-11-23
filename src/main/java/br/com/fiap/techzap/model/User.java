package br.com.fiap.techzap.model;

import br.com.fiap.techzap.controller.dtos.user.UserRegisterDTO;
import br.com.fiap.techzap.controller.dtos.user.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.function.Supplier;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_TZ_USER")
public class User {

    @Column(name = "id_user")
    @Id
    private Long idUser;

    @Column(name = "ds_user_name")
    private String name;

    @Column(name = "ds_surname")
    private String surname;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "ds_password")
    private String password;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_additional_data")
//    private AdditionalData additionaldata;

    public User(UserRegisterDTO userRegisterDTO){
        this.name = userRegisterDTO.name();
        this.surname = userRegisterDTO.surname();
        this.email = userRegisterDTO.email();
        this.password = userRegisterDTO.password();
    }

    public void updateInformation(UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.name() != null) {
            this.name = userUpdateDTO.name();
        }

        if (userUpdateDTO.surname() != null) {
            this.surname = userUpdateDTO.surname();
        }

        if (userUpdateDTO.email() != null) {
            this.email = userUpdateDTO.email();
        }

        if (userUpdateDTO.password() != null) {
            this.password = userUpdateDTO.password();
        }

    }

}
