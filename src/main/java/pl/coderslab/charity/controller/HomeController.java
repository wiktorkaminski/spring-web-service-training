package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CharityUserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CharityUserService charityUserService;

    @ModelAttribute
    public void getLoggedUserName(Authentication authentication, Model model) {
        if (authentication == null) return;

        String userFirstName = charityUserService.getUserFirstName(authentication);
        if (userFirstName != null) {
            model.addAttribute("userFirstName", userFirstName);
        }
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        Iterable<Institution> allInstitutions = institutionRepository.findAll();
        model.addAttribute("institutions", allInstitutions);

        Long donatedBags = donationRepository.countAllDonatedBags();
        model.addAttribute("donatedBags", donatedBags);

        Long countedDonations = donationRepository.count();
        model.addAttribute("countedDonations", countedDonations);
        return "index";
    }
}
