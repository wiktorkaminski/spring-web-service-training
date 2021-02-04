package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public List<InstitutionDTO> getAllInstitutionDTOs() {
        List<Institution> allInstitutions = getAllInstitutions();
        return convertInstitutionsToDTOs(allInstitutions);
    }

    private List<Institution> getAllInstitutions() {
        return (List<Institution>) institutionRepository.findAll();
    }

    private List<InstitutionDTO> convertInstitutionsToDTOs(List<Institution> list) {
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
}
