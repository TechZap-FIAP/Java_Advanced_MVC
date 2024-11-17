package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineDetailedDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineRegisterDTO;
import br.com.fiap.techzap.controller.dtos.typeTurbine.TypeTurbineUpdateDTO;
import br.com.fiap.techzap.model.TypeTurbine;
import br.com.fiap.techzap.service.TypeTurbineService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/type_turbine")
public class TypeTurbineController {

    private final TypeTurbineService typeTurbineService;

    public TypeTurbineController(TypeTurbineService typeTurbineService) { this.typeTurbineService = typeTurbineService; }

    @PostMapping
    public String create(@Valid TypeTurbineRegisterDTO typeTurbineRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        TypeTurbine typeTurbine = typeTurbineService.create(typeTurbineRegisterDTO);

        URI uri = uriBuilder.path("api/type_turbine").buildAndExpand(typeTurbine.getIdTypeTurbine()).toUri();
        redirectAttributes.addFlashAttribute("type_turbine", "Tipo de Turbina criada com sucesso!");
        return "redirect:/type_turbine/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<TypeTurbineDetailedDTO> page = typeTurbineService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("type_turbine/list");
        modelAndView.addObject("type_turbine", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber() );
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        TypeTurbineDetailedDTO typeTurbineDetailedDTO = typeTurbineService.get(id);
        ModelAndView modelAndView = new ModelAndView("type_turbine/edit");
        modelAndView.addObject("type_turbine", typeTurbineDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        typeTurbineService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Tipo de Turbina deletada com successo!");
        return "redirect:/type_turbine/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody TypeTurbineUpdateDTO typeTurbineUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        TypeTurbineDetailedDTO typeTurbineDetailedDTO = typeTurbineService.update(id, typeTurbineUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Tipo de Turbina editada com sucesso!");
        return "redirect:/type_turbine/list";
    }

}
