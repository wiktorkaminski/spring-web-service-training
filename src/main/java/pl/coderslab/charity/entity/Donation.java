package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donations")
@Getter
@Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Positive
    int quantity;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "donations_categories")
    @NotEmpty
    List<Category> categories = new ArrayList<>();

    @ManyToOne
    @NotNull
    Institution institution;

    @NotBlank
    String street;

    @NotBlank
    String city;

    @Column(length = 6)
    @Pattern(regexp = "^([0-9]{2}-[0-9]{3})$")
    String zipCode;

    @Pattern(regexp = "^((\\+48 ?)?[0-9]{3}[- ][0-9]{3}[- ][0-9]{3})$|" +
            "^((\\+48 ?)?[0-9]{9})$|" +
            "^(\\+48 ?)?[0-9]{2}[- ][0-9]{3}[- ][0-9]{2}[- ][0-9]{2}$")
    String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    LocalDate pickUpDate;

    @NotNull
    LocalTime pickUpTime;

    String pickUpComment;


}
