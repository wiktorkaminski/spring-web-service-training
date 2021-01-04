package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.RegistrationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "register/form";
    }

    @PostMapping("/form")
    public String processForm(Model model, User user) {
        registrationService.trimFields(user);

        if (registrationService.checkIfUserExists(user)) {
            registrationService.clearEmailAndPasswordField(user);
            model.addAttribute("userExistsFlag", "true");
            model.addAttribute("user", user);
            return "register/form";
        }
        registrationService.saveUser(user);
        return "redirect:/";
    }

}
