package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Institution;

import java.util.List;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    @Modifying
    @Query("UPDATE Institution i SET i.active=false WHERE i.id=?1")
    void deactivateById(Long id);

    List<Institution> findAllByActive(boolean active);

}
