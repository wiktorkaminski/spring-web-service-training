package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.DonationService;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping("/user/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final CharityUserService charityUserService;

    @ModelAttribute
    public void getLoggedUserName(Authentication authentication, Model model) {
        if (authentication == null) return;

        String userFirstName = charityUserService.getUserFirstName(authentication);
        if (userFirstName != null) {
            model.addAttribute("userFirstName", userFirstName);
        }
    }

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
    public String processForm(@Valid Donation donation, BindingResult bindingResult, Authentication authentication, Model model) {
        if (!bindingResult.hasErrors()) {
            donationService.saveDonation(authentication, donation);
        } else {
            String errorMsg = donationService.prepareErrorMsg(bindingResult.getFieldErrors());
            model.addAttribute("errorMsg", errorMsg);
        }
        return "/donation/form-confirmation";
    }
}
