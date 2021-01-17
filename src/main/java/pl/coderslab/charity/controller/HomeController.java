package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CharityUserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CharityUserService charityUserService;

    @ModelAttribute
    public void getLoggedUserDetails(@AuthenticationPrincipal CharityUser charityUser, Model model) {
        if (charityUser == null) {
            System.out.println("Logged-in user is null");
        }
        if (charityUser != null) {
            UserDTO userDTO = charityUserService.entityToDto(charityUser);
            model.addAttribute("userDetails", userDTO);
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
