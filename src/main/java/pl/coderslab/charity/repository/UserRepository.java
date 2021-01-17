package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.CharityUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<CharityUser, Long> {
    Boolean existsUserByEmail(String email);

    CharityUser findFirstByEmail (String email);

    @Query("SELECT c.firstName FROM CharityUser c WHERE c.email=?1")
    Optional<String> findUserFirstNameByEmail(String email);
}
