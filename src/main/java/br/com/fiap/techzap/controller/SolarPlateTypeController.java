package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlateType.SolarPlateTypeUpdateDTO;
import br.com.fiap.techzap.model.SolarPlateType;
import br.com.fiap.techzap.service.SolarPlateTypeService;
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
@RequestMapping("/api/solar_plate_type")
public class SolarPlateTypeController {

    private final SolarPlateTypeService solarPlateTypeService;

    @Autowired
    public SolarPlateTypeController(SolarPlateTypeService solarPlateTypeService) { this.solarPlateTypeService = solarPlateTypeService; }

    @PostMapping
    public String create(@Valid SolarPlateTypeRegisterDTO solarPlateTypeRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        SolarPlateType plateType = solarPlateTypeService.create(solarPlateTypeRegisterDTO);

        URI uri = uriBuilder.path("api/solar_plate_type").buildAndExpand(plateType.getIdTypeSolarBoard()).toUri();
        redirectAttributes.addFlashAttribute("solarPlateType", "Tipo de Placa Solar criada com sucesso!");
        return "redirect:/solar_plate_type/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<SolarPlateTypeDetailedDTO> page = solarPlateTypeService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("solar_plate_type/list");
        modelAndView.addObject("solar_plate_type", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber() );
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        SolarPlateTypeDetailedDTO solarPlateTypeDetailedDTO = solarPlateTypeService.get(id);
        ModelAndView modelAndView = new ModelAndView("solar_plate_type/edit");
        modelAndView.addObject("solarPlateType", solarPlateTypeDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        solarPlateTypeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Tipo de Placa Solar deletada com successo!");
        return "redirect:/solar_plate_type/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody SolarPlateTypeUpdateDTO solarPlateTypeUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        SolarPlateTypeDetailedDTO solarPlateTypeDetailedDTO = solarPlateTypeService.update(id, solarPlateTypeUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Tipo de Placa Solar editada com sucesso!");
        return "redirect:/solar_plate_type/list";
    }

}
