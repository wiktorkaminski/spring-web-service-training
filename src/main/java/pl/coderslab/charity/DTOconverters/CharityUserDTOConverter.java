package pl.coderslab.charity.DTOconverters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CharityUserDTOConverter {

    private final UserRepository userRepository;

    public CharityUser convert(UserDTO dto) {
        CharityUser entity;

        if (dto.getId() != null) {
            Optional<CharityUser> optionalEntity = userRepository.findById(dto.getId());
            entity = optionalEntity.orElseThrow(EntityNotFoundException::new);
        } else entity = new CharityUser();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public UserDTO convert(CharityUser entity) {
        UserDTO dto = new UserDTO();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }
}
