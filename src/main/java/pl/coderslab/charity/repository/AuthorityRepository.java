package pl.coderslab.charity.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.charity.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
