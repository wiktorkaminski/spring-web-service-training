package pl.coderslab.charity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    Long id;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Length(min = 8, max = 40)
    String password;
}
