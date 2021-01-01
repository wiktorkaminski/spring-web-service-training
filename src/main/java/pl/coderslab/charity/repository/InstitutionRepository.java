package pl.coderslab.charity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Institution;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
}
