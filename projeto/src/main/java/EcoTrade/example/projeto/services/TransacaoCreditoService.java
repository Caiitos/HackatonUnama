package ecotrade.example.projeto.services;

import ecotrade.example.projeto.model.CreditoCarbono;
import ecotrade.example.projeto.model.Role;
import ecotrade.example.projeto.model.TransacaoDeCredito;
import ecotrade.example.projeto.model.Usuario;
import ecotrade.example.projeto.repository.CreditoCarbonoRepository;
import ecotrade.example.projeto.repository.TransacaoCreditoRepository;
import ecotrade.example.projeto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoCreditoService {

    private final CreditoCarbonoRepository creditoCarbonoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TransacaoCreditoRepository transacaoCreditoRepository;

    // Injeção via construtor
    public TransacaoCreditoService(CreditoCarbonoRepository creditoCarbonoRepository,
                                   UsuarioRepository usuarioRepository,
                                   TransacaoCreditoRepository transacaoCreditoRepository) {
        this.creditoCarbonoRepository = creditoCarbonoRepository;
        this.usuarioRepository = usuarioRepository;
        this.transacaoCreditoRepository = transacaoCreditoRepository;
    }

    //  Compra de crédito de carbono
    public TransacaoDeCredito comprarCredito(Long creditoId, Long empresaId, BigDecimal quantidade) {
        CreditoCarbono credito = creditoCarbonoRepository.findById(creditoId)
                .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));

        Usuario empresa = usuarioRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        // Verifica se é uma empresa
        if (empresa.getRole() != Role.COMPANY) {
            throw new RuntimeException("Apenas empresas podem comprar créditos");
        }

        // Valida quantidade informada
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("A quantidade deve ser positiva");
        }

        // Verifica se há créditos disponíveis suficientes
        if (credito.getAvailableQuantity().compareTo(quantidade) < 0) {
            throw new RuntimeException("Quantidade insuficiente de créditos disponíveis");
        }

        // Cria a transação
        TransacaoDeCredito transacao = new TransacaoDeCredito();
        transacao.setCreditoCarbono(credito);
        transacao.setComprador(empresa);
        transacao.setVendedor(credito.getProducer()); // O produtor é o vendedor
        transacao.setQuantidade(quantidade);
        transacao.setPrecoPorUnidade(BigDecimal.valueOf(50.00)); // valor fixo (ou poderia vir de um cálculo)
        transacao.setCriadoEm(LocalDateTime.now());
        transacao.setStatus("CONCLUIDA");

        // Atualiza a quantidade de créditos disponíveis
        BigDecimal novaQuantidade = credito.getAvailableQuantity().subtract(quantidade);
        credito.setAvailableQuantity(novaQuantidade);

        // Desativa o crédito se acabar
        if (novaQuantidade.compareTo(BigDecimal.ZERO) == 0) {
            credito.setActive(false);
        }

        // Salva alterações no banco
        creditoCarbonoRepository.save(credito);
        return transacaoCreditoRepository.save(transacao);
    }

    //  Lista histórico de compras de uma empresa
    public List<TransacaoDeCredito> listarHistoricoCompras(Long empresaId) {
        Usuario empresa = usuarioRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return transacaoCreditoRepository.findByComprador(empresa);
    }

    //  Lista histórico de vendas de um produtor
    public List<TransacaoDeCredito> listarHistoricoVendas(Long produtorId) {
        Usuario produtor = usuarioRepository.findById(produtorId)
                .orElseThrow(() -> new RuntimeException("Produtor não encontrado"));
        return transacaoCreditoRepository.findByVendedor(produtor);
    }
}
