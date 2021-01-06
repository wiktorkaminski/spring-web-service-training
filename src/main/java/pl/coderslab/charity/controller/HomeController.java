package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> allInstitutions = institutionRepository.findDistinctFirst4ByOrderByIdDesc();
        model.addAttribute("institutions", allInstitutions);

        Long donatedBags = donationRepository.countAllDonatedBags();
        model.addAttribute("donatedBags", donatedBags);

        Long countedDonations = donationRepository.count();
        model.addAttribute("countedDonations", countedDonations);
        return "index";
    }
}
