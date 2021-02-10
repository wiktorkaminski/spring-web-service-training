package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.DTOconverters.CharityUserDTOConverter;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharityUserService {

    private final UserRepository userRepository;
    private final CharityUserDTOConverter userDTOConverter;


    public CharityUser dtoToEntity(UserDTO dto) {
        return userDTOConverter.convert(dto);
    }

    public UserDTO entityToDto(CharityUser entity) {
        return userDTOConverter.convert(entity);
    }

    public String getUserFirstName(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        String email = principal.getUsername();
        Optional<String> userFirstName = userRepository.findUserFirstNameByEmail(email);
        return userFirstName.orElse(null);
    }

    public List<Donation> getUserDonations(String email) {
        return userRepository.getDonationsByUserId(email);
    }

    public UserDTO getUserDTOByEmail(String email) {
        CharityUser charityUser = userRepository.findFirstByEmail(email);
        return new UserDTO(
                charityUser.getId(),
                charityUser.getFirstName(),
                charityUser.getLastName(),
                charityUser.getEmail(),
                null
        );
    }

    public CharityUser getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

}
