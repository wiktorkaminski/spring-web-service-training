package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    public void trimFields(User user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
        user.setEmail(user.getEmail().trim());
    }

    public boolean checkIfUserExists(User user) {
        return userRepository.existsUserByEmail(user.getEmail());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void clearEmailAndPasswordField(User user) {
        user.setPassword(null);
        user.setEmail(null);
    }
}
