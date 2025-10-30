package repository;

import entities.TransacaoDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTransicaoDeCredito extends JpaRepository<TransacaoDeCredito, Long> {
}
