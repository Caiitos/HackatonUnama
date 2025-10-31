package ecotrade.example.projeto.services;

import ecotrade.example.projeto.model.CreditoCarbono;
import ecotrade.example.projeto.model.Role;
import ecotrade.example.projeto.model.Usuario;
import ecotrade.example.projeto.repository.CreditoCarbonoRepository;
import ecotrade.example.projeto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditoCarbonoService {

    private final UsuarioRepository usuarioRepository;
    private final CreditoCarbonoRepository creditoCarbonoRepository;

    //  Injeção de dependência via construtor (boa prática)
    public CreditoCarbonoService(UsuarioRepository usuarioRepository,
                                 CreditoCarbonoRepository creditoCarbonoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.creditoCarbonoRepository = creditoCarbonoRepository;
    }

    //  Registra um novo crédito de carbono
    public CreditoCarbono registrarCredito(Long produtorId, String origem, BigDecimal quantidade) {
        Usuario produtor = usuarioRepository.findById(produtorId)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));

        // Verifica se o usuário tem o papel de produtor
        if (produtor.getRole() != Role.PRODUCER) {
            throw new RuntimeException("Apenas produtores podem registrar créditos de carbono");
        }

        // Valida a quantidade
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A quantidade de crédito deve ser positiva");
        }

        // Cria o crédito de carbono
        CreditoCarbono credito = new CreditoCarbono();
        credito.setProducer(produtor);
        credito.setOrigin(origem);
        credito.setAvailableQuantity(quantidade);
        credito.setGenerationDate(LocalDate.now());
        credito.setActive(true);

        // Salva no banco de dados
        return creditoCarbonoRepository.save(credito);
    }

    //  Lista todos os créditos ativos e com quantidade disponível
    public List<CreditoCarbono> listarCreditosDisponiveis() {
        return creditoCarbonoRepository.findByActiveTrue().stream()
                .filter(credito -> credito.getAvailableQuantity() != null &&
                        credito.getAvailableQuantity().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }

    // Lista todos os créditos registrados por um produtor específico
    public List<CreditoCarbono> listarCreditosPorProdutor(Long produtorId) {
        return creditoCarbonoRepository.findByProducerId(produtorId);
    }
}
