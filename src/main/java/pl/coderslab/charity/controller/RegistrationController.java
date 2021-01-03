package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "register/form";
    }

}
