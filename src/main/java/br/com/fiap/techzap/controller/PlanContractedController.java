package br.com.fiap.techzap.controller;

import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedDetailedDTO;
import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedRegisterDTO;
import br.com.fiap.techzap.controller.dtos.planContracted.PlanContractedUpdateDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateDetailedDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateRegisterDTO;
import br.com.fiap.techzap.controller.dtos.solarPlate.SolarPlateUpdateDTO;
import br.com.fiap.techzap.model.PlanContracted;
import br.com.fiap.techzap.model.SolarPlate;
import br.com.fiap.techzap.service.PlanContractedService;
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
@RequestMapping("/plan-contracted")
public class PlanContractedController {

    private final PlanContractedService planContractedService;

    @Autowired
    public PlanContractedController(PlanContractedService planContractedService) { this.planContractedService = planContractedService; }

    @PostMapping
    public String create (@Valid PlanContractedRegisterDTO planContractedRegisterDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        planContractedService.create(planContractedRegisterDTO);
        redirectAttributes.addFlashAttribute("message", "Plano contratado com sucesso!");
        return "redirect:/plan-contracted/list";
    }

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<PlanContractedDetailedDTO> page = planContractedService.list(pageable);
        ModelAndView modelAndView = new ModelAndView("plan-contracted/list");
        modelAndView.addObject("plan-contracted", page.getContent());
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("currentPage", pageable.getPageNumber());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        PlanContractedDetailedDTO planContractedDetailedDTO = planContractedService.get(id);
        ModelAndView modelAndView = new ModelAndView("plan-contracted/edit");
        modelAndView.addObject("plan-contracted", planContractedDetailedDTO);
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        planContractedService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Plano cancelado com sucesso!");
        return "redirect:/plan-contracted/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody PlanContractedUpdateDTO planContractedUpdateDTO, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PlanContractedDetailedDTO planContractedDetailedDTO = planContractedService.update(id, planContractedUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Plano atualizado com sucesso!");
        return "redirect:/plan-contracted/list";
    }

}
