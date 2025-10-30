package repository;

import entities.CreditoCarbono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioCreditoCarbono extends JpaRepository<CreditoCarbono, Long> {

    List <CreditoCarbono> findProducerBId(Long producerId);

    @Query("select c from CreditoCarbono c where c.availableQuantity > 0 and c.active = true")
    List<CreditoCarbono> findAvailableCredits();


}
