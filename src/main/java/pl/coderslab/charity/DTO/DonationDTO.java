package pl.coderslab.charity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class DonationDTO {

    public final Long ID;
    public final int QUANTITY;
    public final List<String> CATEGORIES;
    public final String INSTITUTION_NAME;
    public final LocalDate PICK_UP_DATE;

}
