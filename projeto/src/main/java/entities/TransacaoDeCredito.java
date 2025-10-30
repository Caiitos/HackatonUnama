package entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDeCredito {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private CreditoCarbono creditoCarbono;

    @ManyToOne(optional = false)
    private Usuario comprqador;

    @ManyToOne(optional = false)
    private Usuario vendedor;

    private BigDecimal quantidade;
    private BigDecimal precoPorUnidade;

    private LocalDateTime criadoEm;

    private String status;

    public TransacaoDeCredito(){

    }


}
