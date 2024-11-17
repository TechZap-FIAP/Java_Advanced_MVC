package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateUpdateDTO;
import br.com.fiap.techzap.model.SolarPlate;
import br.com.fiap.techzap.service.SolarPlateService;
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
@RequestMapping("/api/solar_plate")
public class SolarPlateController {

    private SolarPlateService solarPlateService;

    @Autowired
    public SolarPlateController(SolarPlateService solarPlateService) { this.solarPlateService = solarPlateService; }

    @PostMapping
    public String create (@Valid SolarPlateRegisterDTO solarPlateRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        SolarPlate solarPlate = solarPlateService.create(solarPlateRegisterDTO);
        URI uri = uriBuilder.path("/api/solar_plate").buildAndExpand(solarPlate.getIdSolarPlate()).toUri();
        redirectAttributes.addFlashAttribute("message", "Placa Solar criada com sucesso!");
        return "redirect:/solar_plate/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<SolarPlateDetailedDTO> page = solarPlateService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("solar_plate/list");
        modelAndView.addObject("solar_plate", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        SolarPlateDetailedDTO solarPlateDetailedDTO = solarPlateService.get(id);
        ModelAndView modelAndView = new ModelAndView("solar_plate/edit");
        modelAndView.addObject("solar_plate", solarPlateDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        solarPlateService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Placa Solar deletada com sucesso!");
        return "redirect:/solar_plate/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody SolarPlateUpdateDTO solarPlateUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        SolarPlateDetailedDTO solarPlateDetailedDTO = solarPlateService.update(id, solarPlateUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Placa Solar atualizada com sucesso!");
        return "redirect:/solar_plate/list";
    }

}
