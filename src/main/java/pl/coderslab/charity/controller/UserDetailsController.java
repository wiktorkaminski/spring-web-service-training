package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.RegistrationService;
import pl.coderslab.charity.service.UserDetailsService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final CharityUserService charityUserService;
    private final RegistrationService registrationService;

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
        String email = getUserIdentifier(authentication);
        model.addAttribute("donations", userDetailsService.prepareDonations(email));
        return "user-details/donations-list";
    }

    @GetMapping("/my-data")
    public String showUserData(Model model, Authentication authentication) {
        String email = getUserIdentifier(authentication);
        UserDTO userDTO = charityUserService.getUserDTOByEmail(email);
        model.addAttribute("user", userDTO);
        return "/user-details/data-form";
    }

    @PostMapping("/my-data")
    public String processUserDataChanges(@ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult, @RequestParam String password2, Authentication authentication) {
        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            bindingResult.rejectValue("password", "error.userdto", "Wprowadzone hasła nie są zgodne.");
        }
        if (bindingResult.hasErrors()) return "/user-details/data-form";

        CharityUser charityUser = userDetailsService.updateUserDataWithUserDTO(user, getUserIdentifier(authentication));
        registrationService.encodePassword(charityUser);
        registrationService.saveUser(charityUser);
        return "redirect:/";
    }


    private String getUserIdentifier(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername();
    }
}
