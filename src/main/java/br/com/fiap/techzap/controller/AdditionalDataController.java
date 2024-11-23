package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataDetailedDTO;
import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataRegisterDTO;
import br.com.fiap.techzap.controller.dtos.additionalData.AdditionalDataUpdateDTO;
import br.com.fiap.techzap.model.AdditionalData;
import br.com.fiap.techzap.service.AdditionalDataService;
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
@RequestMapping("/additional-data")
public class AdditionalDataController {

    private final AdditionalDataService additionalDataService;

    @Autowired
    public AdditionalDataController(AdditionalDataService additionalDataService) { this.additionalDataService = additionalDataService; }

    @PostMapping
    public String create(@Valid AdditionalDataRegisterDTO additionalDataRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        additionalDataService.create(additionalDataRegisterDTO);
        redirectAttributes.addFlashAttribute("message", "Informações adicionais criada com sucesso!");
        return "redirect:/additional-data/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<AdditionalDataDetailedDTO> page = additionalDataService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("additional-data/list");
        modelAndView.addObject("additional-data", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        AdditionalDataDetailedDTO additionalDataDetailedDTO = additionalDataService.get(id);
        ModelAndView modelAndView = new ModelAndView("additional-data/edit");
        modelAndView.addObject("additional-data", additionalDataDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        additionalDataService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Informações adicionais deletados com sucesso!");
        return "redirect:/additional-data/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody AdditionalDataUpdateDTO additionalDataUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        AdditionalDataDetailedDTO additionalDataDetailedDTO = additionalDataService.update(id, additionalDataUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Informações adicionais atualizado com sucesso!");
        return "redirect:/additional-data/list";
    }

}
