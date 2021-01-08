package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "donations")
@Getter @Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @PositiveOrZero
    int quantity;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable (name = "donations_categories")
    List<Category> categories = new ArrayList<>();

    @ManyToOne
    Institution institution;

    String street;

    String city;

    @Column(length = 6)
    String zipCode;

    String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate pickUpDate;

    LocalTime pickUpTime;

    String pickUpComment;
}
