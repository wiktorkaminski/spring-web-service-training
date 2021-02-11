package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.DTOconverters.CharityUserDTOConverter;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;
    private final CharityUserDTOConverter userDTOConverter;

    public List<UserDTO> getAllUserDTOsByAuthority(String authority) {
        List<CharityUser> adminList = userRepository.getAllByAuthoritiesContaining("ROLE_ADMIN");
        List<UserDTO> adminDTOsList = new ArrayList<>();

        for (CharityUser charityUser : adminList) {
            adminDTOsList.add(userDTOConverter.convert(charityUser));
        }
        return adminDTOsList;
    }

    public UserDTO getUserDTObyId(Long id) {
        CharityUser userEntity = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userDTOConverter.convert(userEntity);
    }

}
