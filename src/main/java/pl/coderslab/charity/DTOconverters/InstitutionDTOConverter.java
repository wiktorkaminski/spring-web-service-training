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
        if (dto.getId() != null) {
            Optional<Institution> optionalEntity = institutionRepository.findById(dto.getId());
            Institution entity = optionalEntity.orElseThrow(EntityNotFoundException::new);
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            return entity;
        }

        return new Institution(
                    dto.getId(),
                    dto.getName(),
                    dto.getDescription()
            );
    }

}
