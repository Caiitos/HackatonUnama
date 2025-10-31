package ecotrade.example.projeto.repository;

import ecotrade.example.projeto.model.CreditoCarbono;
import ecotrade.example.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditoCarbonoRepository extends JpaRepository<CreditoCarbono, Long> {
    List<CreditoCarbono> findByProducerAndActiveTrue(Usuario producer);
    List<CreditoCarbono> findByActiveTrue();
    List<CreditoCarbono> findByOriginContainingIgnoreCase(String origin);
    List<CreditoCarbono> findByProducerId(Long produtorId);
}