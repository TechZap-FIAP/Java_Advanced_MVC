package br.com.fiap.techzap.controller.dtos.user;

import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataDetailedDTO;
import br.com.fiap.techzap.model.User;

public record UserDetailedDTO(

        Long idUser,
        String name,
        String surname,
        String email
) {

    public UserDetailedDTO (User user) {
        this(user.getIdUser(), user.getName(), user.getSurname(), user.getEmail());
    }

}
