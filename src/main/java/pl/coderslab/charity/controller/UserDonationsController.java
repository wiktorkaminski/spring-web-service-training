package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Authority;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDonationsController {


    @GetMapping("/my-donations")
    public String showUserDonations(Authority authority) {
        return "user-details/donations-list";
    }
}
