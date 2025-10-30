package repository;

import model.TransacaoDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTransicaoDeCredito extends JpaRepository<TransacaoDeCredito, Long> {


}
