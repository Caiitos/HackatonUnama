package ecotrade.example.projeto.repository;

import ecotrade.example.projeto.model.CreditoCarbono;
import ecotrade.example.projeto.model.TransacaoDeCredito;
import ecotrade.example.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransacaoCreditoRepository extends JpaRepository<TransacaoDeCredito, Long> {
    List<TransacaoDeCredito> findByComprador(Usuario comprador);
    List<TransacaoDeCredito> findByVendedor(Usuario vendedor);
    List<TransacaoDeCredito> findByStatus(String status);
    
}