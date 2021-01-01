package pl.coderslab.charity.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "institutions")
@Getter
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

}
