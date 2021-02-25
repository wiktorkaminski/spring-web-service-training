package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharityUserService {

    private final UserRepository userRepository;

    public CharityUser dtoToEntity(UserDTO dto) {
        CharityUser entity = new CharityUser();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public UserDTO entityToDto(CharityUser entity) {
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    public String getUserFirstName(Authentication authentication) {
        CharityUser principal = (CharityUser) authentication.getPrincipal();
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
