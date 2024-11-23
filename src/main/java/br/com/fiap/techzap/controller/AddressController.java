package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.address.AddressDetailedDTO;
import br.com.fiap.techzap.controller.dtos.address.AddressRegisterDTO;
import br.com.fiap.techzap.controller.dtos.address.AddressUpdateDTO;
import br.com.fiap.techzap.model.Address;
import br.com.fiap.techzap.service.AddressService;
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
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) { this.addressService = addressService; }

    @PostMapping
    public String create(@Valid AddressRegisterDTO addressRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        addressService.create(addressRegisterDTO);
        redirectAttributes.addFlashAttribute("message", "Endereço Criado com Sucesso!");
        return "redirect:/address/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<AddressDetailedDTO> page = addressService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("address/list");
        modelAndView.addObject("address", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        AddressDetailedDTO addressDetailedDTO = addressService.get(id);
        ModelAndView modelAndView = new ModelAndView("address/edit");
        modelAndView.addObject("address", addressDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        addressService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Endereço removido!" );
        return "redirect:/address/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody AddressUpdateDTO addressUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AddressDetailedDTO addressDetailedDTO = addressService.update(id, addressUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Endereço atualizado com sucesso!");
        return "redirect:/address/list";
    }

}
