package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.user.UserDetailedDTO;
import br.com.fiap.techzap.controller.dtos.user.UserRegisterDTO;
import br.com.fiap.techzap.controller.dtos.user.UserUpdateDTO;
import br.com.fiap.techzap.model.User;
import br.com.fiap.techzap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public String create (@Valid UserRegisterDTO userRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes){
        User user = userService.create(userRegisterDTO);

        URI uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getIdUser()).toUri();
        redirectAttributes.addFlashAttribute("message", "Usuário Criado com Sucesso!");

        return "redirect:/users/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<UserDetailedDTO> page = userService.list(pagination);
        ModelAndView modelAndView = new ModelAndView("users/list");
        modelAndView.addObject("users", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pagination.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView find(@PathVariable("id") Long id) {
        UserDetailedDTO userDetailedDTO = userService.get(id);
        ModelAndView modelAndView = new ModelAndView("users/edit");
        modelAndView.addObject("user", userDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Usuário removido");
        return "redirect:/users/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody UserUpdateDTO userUpdateDTO, RedirectAttributes redirectAttributes) {
        UserDetailedDTO userDetailedDTO = userService.update(id, userUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Usuário atualizado");
        return "redirect:/users/list";
    }


}
