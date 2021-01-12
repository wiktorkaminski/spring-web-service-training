package pl.coderslab.charity.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsUserByEmail(String email);
}
