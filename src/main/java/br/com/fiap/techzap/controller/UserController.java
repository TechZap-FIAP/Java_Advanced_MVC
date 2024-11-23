package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.user.UserDetailedDTO;
import br.com.fiap.techzap.controller.dtos.user.UserRegisterDTO;
import br.com.fiap.techzap.controller.dtos.user.UserUpdateDTO;
import br.com.fiap.techzap.model.User;
import br.com.fiap.techzap.repository.UserRepository;
import br.com.fiap.techzap.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("create")
    public String createUserView(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("create")
    @Transactional
    public String createUserAction(@Valid User user, BindingResult result, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        model.addAttribute("message", "Usuário cadastrado!");

        return "redirect:auth/login";
    }

    @GetMapping("profile/{id}")
    public String getUserProfile(@PathVariable Long id, Model model, User user) {
//        User loggedUser = userService.findByEmail(user.getEmail());
//        if (loggedUser.getIdUser() == null) {
//            return "error/accessDenied"; // Página de acesso negado
//        }

        userService.findById(id);
        model.addAttribute("user", id);
        return "user/profile"; // Nome da view Thymeleaf
    }

    /*

    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable("id") Long id) {
        UserDetailedDTO userDetailedDTO = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("user/edit");
        modelAndView.addObject("user", userDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Usuário removido");
        return "redirect:/user/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO, RedirectAttributes redirectAttributes) {
        UserDetailedDTO userDetailedDTO = userService.update(id, userUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Usuário atualizado");
        return "redirect:/user/list";
    }

    */


}
