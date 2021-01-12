package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    public Iterable<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public String prepareErrorMsg(List<FieldError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("Errors: \n");
        for (FieldError err : errors) {
            sb.append(err.getField());
            sb.append(" : ");sb.append(err.getDefaultMessage());
            sb.append("\n");

        }
        return sb.toString();
    }
}
