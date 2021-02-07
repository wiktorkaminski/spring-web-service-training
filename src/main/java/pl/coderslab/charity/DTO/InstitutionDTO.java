package pl.coderslab.charity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstitutionDTO {

    private Long id;

    @NotBlank
    @Length(max = 255)
    private String name;

    @NotBlank
    @Length(max = 255)
    private String description;

    private Long donations;

    private boolean active;
}
