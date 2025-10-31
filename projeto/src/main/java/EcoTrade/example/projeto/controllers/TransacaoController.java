package ecotrade.example.projeto.controllers;

import ecotrade.example.projeto.model.TransacaoDeCredito;
import ecotrade.example.projeto.services.TransacaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoCreditoService transacaoService;

    @PostMapping("/comprar")
    public ResponseEntity<TransacaoDeCredito> comprarCredito(
            @RequestParam Long creditoId,
            @RequestParam Long empresaId,
            @RequestParam BigDecimal quantidade) {

        try {
            TransacaoDeCredito transacao = transacaoService.comprarCredito(creditoId, empresaId, quantidade);
            return ResponseEntity.ok(transacao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/empresa/{empresaId}/compras")
    public List<TransacaoDeCredito> historicoCompras(@PathVariable Long empresaId) {
        return transacaoService.listarHistoricoCompras(empresaId);
    }

    @GetMapping("/produtor/{produtorId}/vendas")
    public List<TransacaoDeCredito> historicoVendas(@PathVariable Long produtorId) {
        return transacaoService.listarHistoricoVendas(produtorId);
    }
}