package pl.coderslab.charity.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.CharityUser;

public interface UserRepository extends CrudRepository<CharityUser, Long> {
    Boolean existsUserByEmail(String email);

    CharityUser findFirstByEmail (String email);
}
