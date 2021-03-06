package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.DTOconverters.InstitutionDTOConverter;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final InstitutionDTOConverter dtoConverter;

    public List<InstitutionDTO> getAllInstitutionDTOs(boolean showActive) {
        List<Institution> allInstitutions = getAllInstitutions(showActive);
        return prepareInstitutionsDTOList(allInstitutions);
    }

    public Institution save(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Transactional
    public void toggleActivationById(Long id, boolean isActive) {
        institutionRepository.toggleActivationById(id, isActive);
    }

    public Institution convert(InstitutionDTO institutionDTO) {
        return dtoConverter.toEntity(institutionDTO);
    }

    public InstitutionDTO convert(Institution institution) {
        return dtoConverter.toDTO(institution);
    }

    private List<Institution> getAllInstitutions(boolean active) {
        return (List<Institution>) institutionRepository.findAllByActive(active);
    }
    private List<InstitutionDTO> prepareInstitutionsDTOList(List<Institution> list) {
        List<InstitutionDTO> dtosList = new ArrayList<>();
        for (Institution institution : list) {
            dtosList.add(new InstitutionDTO(
                    institution.getId(),
                    institution.getName(),
                    institution.getDescription(),
                    donationRepository.countAllByInstitutionId(institution.getId()),
                    institution.isActive()
            ));
        }
        return dtosList;
    }

    public InstitutionDTO getInstitutionDTOById(Long id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return this.convert(institution);

    }


}
