package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public void trimFields(UserDTO user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        user.setEmail(user.getEmail().trim());
    }

    public boolean checkIfUserExists(UserDTO user) {
        return userRepository.existsUserByEmail(user.getEmail());
    }

    public boolean checkIfUserExists(CharityUser user) {
        return userRepository.existsUserByEmail(user.getEmail());
    }

    public CharityUser saveUser(CharityUser user) {
        return userRepository.save(user);
    }

    public void encodePassword(CharityUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public void grantAuthoritiesToUser(CharityUser charityUser, String[] authorities) {
        String email = charityUser.getEmail();
        List<Authority> userAuthorities = new ArrayList<>();
        for (String authority : authorities) {
            Authority ua = new Authority(null, email, authority);
            Authority savedAuthority = authorityRepository.save(ua);
            userAuthorities.add(savedAuthority);
        }
        charityUser.setAuthorities(userAuthorities);
    }

}

