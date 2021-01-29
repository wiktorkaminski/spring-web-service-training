package pl.coderslab.charity.DTO;

import lombok.AllArgsConstructor;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class DonationDTO {

    public final Long ID;
    public final int QUANTITY;
    public final List<Category> CATEGORIES;
    public final Institution INSTITUTION;
    public final LocalDate PICK_UP_DATE;

}
