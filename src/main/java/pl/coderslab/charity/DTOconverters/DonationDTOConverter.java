package pl.coderslab.charity.DTOconverters;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonationDTOConverter {

    public DonationDTO toDTO (Donation entity) {
        List<String> categories = new ArrayList<>();
        for (Category category : entity.getCategories()) {
            categories.add(category.getName());
        }

        return new DonationDTO(
                entity.getId(),
                entity.getQuantity(),
                categories,
                entity.getInstitution().getName(),
                entity.getPickUpDate()
        );
    }

    public List<DonationDTO> toDTOList(List<Donation> list) {
        List<DonationDTO> result = new ArrayList<>();
        for (Donation donation : list) {
            result.add(this.toDTO(donation));
        }
        return result;
    }
}
