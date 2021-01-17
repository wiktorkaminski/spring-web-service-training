package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.UserAuthority;
import pl.coderslab.charity.repository.UserAuthorityRepository;
import pl.coderslab.charity.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityRepository userAuthorityRepository;

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

    public void grantAuthoritiesToUser(String email, String[] authorities) {
        for (String authority : authorities) {
            UserAuthority ua = new UserAuthority(null, email, authority);
            userAuthorityRepository.save(ua);
        }
    }

}
