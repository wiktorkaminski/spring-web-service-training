package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.service.RegistrationService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new CharityUser());
        return "register/form";
    }

    @PostMapping("/form")
    public String processForm(Model model, @ModelAttribute("user") @Valid CharityUser user, BindingResult bindingResult,
                              @RequestParam String password2) {


        if (registrationService.checkIfUserExists(user)) {
            bindingResult.rejectValue("email", "error.charityuser", "Podany email jest już zajęty.");
        }

        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            bindingResult.rejectValue("password", "error.charityuser", "Wprowadzone hasła nie są zgodne.");
        }

        if (bindingResult.hasErrors()) return "register/form";

        registrationService.trimFields(user);
        registrationService.saveUser(user);
        return "redirect:/";
    }

}
