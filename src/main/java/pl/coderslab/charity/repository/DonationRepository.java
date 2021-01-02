package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Long> {

    @Query(
            value = "SELECT SUM(quantity) FROM donations;",
            nativeQuery = true
    )
    Long countAllDonatedBags();

    @Query(
            value = "SELECT COUNT(*) FROM donations;",
            nativeQuery = true
    )
    Long countAllDonations();

}
