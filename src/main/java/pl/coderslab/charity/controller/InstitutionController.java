package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("/list")
    public String listOfActiveInstit(Model model) {
        model.addAttribute("institutions", institutionService.getAllInstitutionDTOs(true));
        return "admin/institutions";
    }

    @GetMapping("/list-deactivated")
    public String listOfDeactivatedIntit(Model model) {
        model.addAttribute("institutions", institutionService.getAllInstitutionDTOs(false));
        return "admin/institutions";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("institutionDTO", new InstitutionDTO());
        return "/admin/institution-form";
    }

    @PostMapping("/form")
    public String processForm(@Valid InstitutionDTO institutionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/institution-form";
        }

        Institution institution = institutionService.convert(institutionDTO);
        institutionService.save(institution);
        return "redirect:/admin/institutions/list";
    }

    @GetMapping("/details-{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        InstitutionDTO institutionDTO = institutionService.getInstitutionDTOById(id);
        model.addAttribute("institutionDTO", institutionDTO);
        return "/admin/institution-details";
    }

    @PostMapping("/update")
    public String update(@RequestParam(defaultValue = "") String toggle, InstitutionDTO institutionDTO) {

        if (toggle.equals("Activate/Show")) {
            institutionService.toggleActivationById(institutionDTO.getId(), true);
            return "redirect:/admin/institutions/list-deactivated";
        }

        if (toggle.equals("Deactivate/Hide")) {
            institutionService.toggleActivationById(institutionDTO.getId(), false);
            return "redirect:/admin/institutions/list";
        }

        Institution institution = institutionService.convert(institutionDTO);
        institutionService.save(institution);

        if (institutionDTO.isActive()) {
            return "redirect:/admin/institutions/list";
        } else return "redirect:/admin/institutions/list-deactivated";


    }
}
