package pl.coderslab.charity.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "user_authorities",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "authority"})}
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String authority;

}
