package br.com.fiap.techzap.service;

import br.com.fiap.techzap.controller.dtos.user.UserDetailedDTO;
import br.com.fiap.techzap.controller.dtos.user.UserRegisterDTO;
import br.com.fiap.techzap.controller.dtos.user.UserUpdateDTO;
import br.com.fiap.techzap.model.User;
import br.com.fiap.techzap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User find(Long id) {
        return userRepository.findById(id).get();
    }

    public User create(UserRegisterDTO userRegisterDTO){
        User user = new User(userRegisterDTO);
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
        return userRepository.save(user);
    }

    public Page<UserDetailedDTO> list(Pageable pageable){
        return userRepository.findAll(pageable).map(UserDetailedDTO::new);
    }

    public UserDetailedDTO get(Long id){
        return new UserDetailedDTO(userRepository.findById(id).get());
    }

    public void delete(Long id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public UserDetailedDTO update(Long id, UserUpdateDTO userUpdateDTO){
        User user = userRepository.findById(id).get();

        user.updateInformation(userUpdateDTO);

        if (userUpdateDTO.password() != null && !passwordEncoder.matches(userUpdateDTO.password(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.password()));
        }
        userRepository.save(user);

        return new UserDetailedDTO(user);

    }

}
