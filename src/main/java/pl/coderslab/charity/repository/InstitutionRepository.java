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
    @Query("UPDATE Institution i SET i.active=?2 WHERE i.id=?1")
    void toggleActivationById(Long id, boolean active);

    List<Institution> findAllByActive(boolean active);

}
