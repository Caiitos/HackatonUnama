package ecotrade.example.projeto.controllers;

import ecotrade.example.projeto.model.CreditoCarbono;
import ecotrade.example.projeto.services.CreditoCarbonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditoCarbonoController {

    @Autowired
    private CreditoCarbonoService creditoCarbonoService;

    @PostMapping("/produtor/{produtorId}")
    public ResponseEntity<CreditoCarbono> registrarCredito(
            @PathVariable Long produtorId,
            @RequestParam String origem,
            @RequestParam BigDecimal quantidade) {

        try {
            CreditoCarbono credito = creditoCarbonoService.registrarCredito(produtorId, origem, quantidade);
            return ResponseEntity.ok(credito);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/disponiveis")
    public List<CreditoCarbono> listarCreditosDisponiveis() {
        return creditoCarbonoService.listarCreditosDisponiveis();
    }

    @GetMapping("/produtor/{produtorId}")
    public List<CreditoCarbono> listarCreditosProdutor(@PathVariable Long produtorId) {
        return creditoCarbonoService.listarCreditosPorProdutor(produtorId);
    }
}
