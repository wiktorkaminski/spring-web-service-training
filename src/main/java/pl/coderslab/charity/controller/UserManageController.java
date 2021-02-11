package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.UserManageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserManageController {

    private final UserRepository userRepository;
    private final CharityUserService charityUserService;
    private final UserManageService userManageService;

    @GetMapping("admins/list")
    public String listAllAdmins(Model model) {
        List<UserDTO> adminList = userManageService.getAllUserDTOsByAuthority("ROLE_USER");
        model.addAttribute("users", adminList);
        model.addAttribute("listType", "admin");
        return "admin/users";
    }

    @GetMapping("admins/details-{id}")
    public String showDetails(Model model, @PathVariable Long id) {
        UserDTO userDTObyId = userManageService.getUserDTObyId(id);
        model.addAttribute("user", userDTObyId);
        model.addAttribute("userType", "admin");
        return "admin/user-details";
    }


//    @GetMapping("users/list")
//    public String listAllUsers(Model model) {
//        List<CharityUser> adminList = userRepository.getAllByAuthoritiesContaining("ROLE_USER");
//        model.addAttribute("users", adminList);
//        model.addAttribute("listType", "user");
//        return "admin/users";
//    }
}
