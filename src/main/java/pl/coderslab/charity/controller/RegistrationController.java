package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.RegistrationService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final CharityUserService charityUserService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register/form";
    }

    @PostMapping("/form")
    public String processForm(Model model, @ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult,
                              @RequestParam String password2) {

        registrationService.trimFields(user);
        
        if (registrationService.checkIfUserExists(user)) {
            bindingResult.rejectValue("email", "error.userdto", "Podany email jest już zajęty.");
        }

        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            bindingResult.rejectValue("password", "error.userdto", "Wprowadzone hasła nie są zgodne.");
        }

        if (bindingResult.hasErrors()) return "register/form";

        CharityUser charityUser = charityUserService.dtoToEntity(user);

        registrationService.grantAuthoritiesToUser(charityUser, new String[]{"ROLE_USER"});
        registrationService.saveUser(charityUser);
        return "redirect:/login";
    }

}
