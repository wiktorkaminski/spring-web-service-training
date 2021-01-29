package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    public Iterable<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Donation saveDonation(Authentication authentication, Donation donation) {
        Donation savedDonation = donationRepository.save(donation);
        this.addDonationToCharityUser(authentication, savedDonation);
        return savedDonation;
    }

    private void addDonationToCharityUser(Authentication authentication, Donation newDonation) {
        User principal = (User) authentication.getPrincipal();
        String email = principal.getUsername();
        CharityUser charityUser = userRepository.findFirstByEmail(email);
        if (charityUser != null) {
            charityUser.getDonations().add(newDonation);
            userRepository.save(charityUser);
        }
    }

    public String prepareErrorMsg(List<FieldError> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("Errors: \n");
        for (FieldError err : errors) {
            sb.append(err.getField());
            sb.append(" : ").append(err.getDefaultMessage());
            sb.append("\n");

        }
        return sb.toString();
    }
}
