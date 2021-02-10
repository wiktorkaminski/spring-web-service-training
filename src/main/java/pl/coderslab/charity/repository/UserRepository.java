package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.CharityUser;
import pl.coderslab.charity.entity.Donation;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<CharityUser, Long> {
    Boolean existsUserByEmail(String email);

    CharityUser findFirstByEmail (String email);

    Optional<CharityUser> findById(Long id);

    @Query("SELECT c.firstName FROM CharityUser c WHERE c.email=?1")
    Optional<String> findUserFirstNameByEmail(String email);

    @Query("SELECT c.donations FROM CharityUser c WHERE c.email = ?1")
    List<Donation> getDonationsByUserId (String email);

    @Query(value = "SELECT * FROM users JOIN users_authorities ua on users.id = ua.charity_user_id JOIN authorities a on a.id = ua.authorities_id\n" +
            "WHERE a.authority = :authority", nativeQuery = true)
    List<CharityUser> getAllByAuthoritiesContaining(String authority);

}
