package pl.coderslab.charity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InstitutionDTO {

    private Long id;
    private String name;
    private String description;
    private Long donations;
}
