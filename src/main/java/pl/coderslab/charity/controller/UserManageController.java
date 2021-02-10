package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CharityUserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserManageController {

    private final UserRepository userRepository;
    private final CharityUserService charityUserService;

    @GetMapping("admins/list")
    public String listAllAdmins(Model model) {
        List<CharityUser> adminList = userRepository.getAllByAuthoritiesContaining("ROLE_ADMIN");
        model.addAttribute("users", adminList);
        model.addAttribute("listType", "admin");
        return "admin/users";
    }


//    @GetMapping("users/list")
//    public String listAllUsers(Model model) {
//        List<CharityUser> adminList = userRepository.getAllByAuthoritiesContaining("ROLE_USER");
//        model.addAttribute("users", adminList);
//        model.addAttribute("listType", "user");
//        return "admin/users";
//    }
}
