package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;

@Service
public class CharityUserService {

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
}
