package pl.coderslab.charity.entity;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @PositiveOrZero
    int quantity;

    @OneToMany
    Set<Category> categories = new HashSet<>();

    @ManyToOne
    Institution institution;

    String street;

    String city;

    @Column(length = 6)
    String zipCode;

    LocalDate pickUpDate;

    LocalTime pickUpTime;

    String pickUpComment;
}
