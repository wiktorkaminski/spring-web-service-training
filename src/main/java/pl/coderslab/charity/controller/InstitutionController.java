package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.InstitutionService;

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
}
