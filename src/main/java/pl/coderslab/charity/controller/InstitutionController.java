package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String list(Model model) {
        model.addAttribute("institutions", institutionService.getAllInstitutionDTOs());
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
}
