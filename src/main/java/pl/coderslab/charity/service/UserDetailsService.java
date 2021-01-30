package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.DTOconverters.DonationDTOConverter;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Donation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final CharityUserService charityUserService;
    private final DonationDTOConverter donationDTOConverter;

    public List<DonationDTO> prepareDonations(String email) {
        List<Donation> donations = charityUserService.getUserDonations(email);
        return donationDTOConverter.toDTOList(donations);
    }

    public CharityUser updateUserDataWithUserDTO(UserDTO userDTO, String email) {
        CharityUser charityUser = charityUserService.getUserByEmail(email);
        charityUser.setFirstName(userDTO.getFirstName());
        charityUser.setLastName(userDTO.getLastName());
        charityUser.setPassword(userDTO.getPassword());
        return charityUser;
    }
}
