package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.DTOconverters.CharityUserDTOConverter;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserManageService {

    private final UserRepository userRepository;
    private final CharityUserDTOConverter userDTOConverter;
    private final AuthorityRepository authorityRepository;

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

    public CharityUser saveAsEntity(UserDTO userDTO) {
        CharityUser charityUser = userDTOConverter.convert(userDTO);
        grantAdminAuth(charityUser);
        return userRepository.save(charityUser);
    }

    private CharityUser grantAdminAuth(CharityUser charityUser) {
        if (!authorityRepository.existsByEmailAndAuthority(charityUser.getEmail(), "ROLE_ADMIN")) {
            List<Authority> auth = (List<Authority>) charityUser.getAuthorities();
            if (auth == null) auth = new ArrayList<>();
            Authority a = new Authority(null, charityUser.getEmail(), "ROLE_ADMIN");
            auth.add(a);
            charityUser.setAuthorities(auth);
        }
        return charityUser;
    }

}
