package pl.coderslab.charity.DTOconverters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InstitutionDTOConverter {

    private final InstitutionRepository institutionRepository;

    public InstitutionDTO toDTO(Institution entity) {
        return new InstitutionDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                null
        );
    }

    public Institution toEntity(InstitutionDTO dto) {
        Optional<Institution> optionalEntity = institutionRepository.findById(dto.getId());
        if (optionalEntity.isPresent()) {
            Institution entity = optionalEntity.get();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            return entity;
        } else {
            return new Institution(
                    null,
                    dto.getName(),
                    dto.getDescription()
            );
        }
    }

}
