package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

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

}
