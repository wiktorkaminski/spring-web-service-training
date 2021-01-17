package pl.coderslab.charity.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.UserAuthority;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
}
