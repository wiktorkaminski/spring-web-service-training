package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CharityUserService;
import pl.coderslab.charity.service.RegistrationService;
import pl.coderslab.charity.service.UserManageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserManageController {

    private final UserManageService userManageService;
    private final RegistrationService registrationService;

    @GetMapping("admins/list")
    public String listAllAdmins(Model model) {
        List<UserDTO> adminList = userManageService.getAllUserDTOsByAuthority("ROLE_USER");
        model.addAttribute("users", adminList);
        model.addAttribute("listType", "admin");
        return "admin/users";
    }

    @GetMapping("admins/form")
    public String form(Model model) {
        model.addAttribute("userType", "admin");
        model.addAttribute("userdto", new UserDTO());
        return "admin/user-form";
    }

    @PostMapping("admins/form")
    public String processForm(@ModelAttribute("userdto") @Valid UserDTO userdto, BindingResult bindingResult, @RequestParam String password2) {
        registrationService.trimFields(userdto);

        if (userdto.getId() == null && registrationService.checkIfUserExists(userdto)) {
            bindingResult.rejectValue("email", "error.userdto", "User with such e-mail exists.");
        }

        if (userdto.getPassword() != null && !userdto.getPassword().equals(password2)) {
            bindingResult.rejectValue("password", "error.userdto", "Passwords do not match.");
        }

        if (bindingResult.hasErrors()) {
            if (userdto.getId() == null) return "admin/user-form";
            else return "admin/user-details";
        };

        userManageService.saveAsEntity(userdto);
        return "redirect:/admin/admins/list";
    }

    @GetMapping("admins/details-{id}")
    public String showDetails(Model model, @PathVariable Long id) {
        UserDTO userDTObyId = userManageService.getUserDTObyId(id);
        model.addAttribute("userdto", userDTObyId);
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
