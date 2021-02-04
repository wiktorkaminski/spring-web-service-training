package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionController {


    @GetMapping("/list")
    public String list() {
        return "admin/institutions";
    }
}
