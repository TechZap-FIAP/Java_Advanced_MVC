package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateUpdateDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindDetailedDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindRegisterDTO;
import br.com.fiap.techzap.controller.dtos.turbineWind.TurbineWindUpdateDTO;
import br.com.fiap.techzap.model.SolarPlate;
import br.com.fiap.techzap.model.TurbineWind;
import br.com.fiap.techzap.service.SolarPlateService;
import br.com.fiap.techzap.service.TurbineWindService;
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
@RequestMapping("/turbine-wind")
public class TurbineWindController {

    private final TurbineWindService turbineWindService;

    @Autowired
    public TurbineWindController(TurbineWindService turbineWindService) { this.turbineWindService = turbineWindService; }

    @PostMapping
    public String create (@Valid TurbineWindRegisterDTO turbineWindRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        turbineWindService.create(turbineWindRegisterDTO);
        redirectAttributes.addFlashAttribute("message", "Turbina Eólica criada com sucesso!");
        return "redirect:/turbine-wind/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<TurbineWindDetailedDTO> page = turbineWindService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("turbine-wind/list");
        modelAndView.addObject("turbine-wind", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        TurbineWindDetailedDTO turbineWindDetailedDTO = turbineWindService.get(id);
        ModelAndView modelAndView = new ModelAndView("turbine-wind/edit");
        modelAndView.addObject("turbine-wind", turbineWindDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        turbineWindService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Turbina Eólica deletada com sucesso!");
        return "redirect:/turbine-wind/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody TurbineWindUpdateDTO turbineWindUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        TurbineWindDetailedDTO turbineWindDetailedDTO = turbineWindService.update(id, turbineWindUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Turbina Eólica atualizada com sucesso!");
        return "redirect:/turbine-wind/list";
    }

}
