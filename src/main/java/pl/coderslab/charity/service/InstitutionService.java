package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.DTOconverters.InstitutionDTOConverter;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final InstitutionDTOConverter dtoConverter;

    public List<InstitutionDTO> getAllInstitutionDTOs() {
        List<Institution> allInstitutions = getAllInstitutions();
        return prepareInstitutionsDTOList(allInstitutions);
    }

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }

    public Institution convert(InstitutionDTO institutionDTO) {
        return dtoConverter.toEntity(institutionDTO);
    }

    public InstitutionDTO convert(Institution institution) {
        return dtoConverter.toDTO(institution);
    }

    private List<Institution> getAllInstitutions() {
        return (List<Institution>) institutionRepository.findAll();
    }

    private List<InstitutionDTO> prepareInstitutionsDTOList(List<Institution> list) {
        List<InstitutionDTO> dtosList = new ArrayList<>();
        for (Institution institution : list) {
            dtosList.add(new InstitutionDTO(
                    institution.getId(),
                    institution.getName(),
                    institution.getDescription(),
                    donationRepository.countAllByInstitutionId(institution.getId())
            ));
        }
        return dtosList;
    }

    public InstitutionDTO getInstitutionDTOById(Long id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return this.convert(institution);

    }


}
