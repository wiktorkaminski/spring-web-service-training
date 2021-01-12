package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping("/form")
    public String form(Model model) {
        Iterable<Institution> allInstitutions = donationService.findAllInstitutions();
        model.addAttribute("institutions", allInstitutions);

        Iterable<Category> allCategories = donationService.findAllCategories();
        model.addAttribute("categories", allCategories);

        model.addAttribute("donation", new Donation());
        return "/donation/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid Donation donation, BindingResult bindingResult) {
        donationService.saveDonation(donation);
        return "redirect:/";
    }
}
