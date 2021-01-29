package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.UserDetailsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final CharityUserService charityUserService;

    @ModelAttribute
    public void getLoggedUserName(Authentication authentication, Model model) {
        if (authentication == null) return;

        String userFirstName = charityUserService.getUserFirstName(authentication);
        if (userFirstName != null) {
            model.addAttribute("userFirstName", userFirstName);
        }
    }

    @GetMapping("/my-donations")
    public String showUserDonations(Model model, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        String email = principal.getUsername();
        model.addAttribute("donations", userDetailsService.prepareDonations(email));
        return "user-details/donations-list";
    }
}
