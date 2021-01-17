package pl.coderslab.charity.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(
        name = "authorities",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "authority"})}
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String authority;

}
