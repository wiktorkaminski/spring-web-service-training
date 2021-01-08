package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @RequestMapping("/")
    public String homeAction(Model model){
        Iterable<Institution> allInstitutions = institutionRepository.findAll();
        model.addAttribute("institutions", allInstitutions);

        Long donatedBags = donationRepository.countAllDonatedBags();
        model.addAttribute("donatedBags", donatedBags);

        Long countedDonations = donationRepository.count();
        model.addAttribute("countedDonations", countedDonations);
        return "index";
    }
}
