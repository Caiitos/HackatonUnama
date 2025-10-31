package ecotrade.example.projeto.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransacaoDeCredito {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private CreditoCarbono creditoCarbono;

    @ManyToOne(optional = false)
    private Usuario comprador;

    @ManyToOne(optional = false)
    private Usuario vendedor;

    private BigDecimal quantidade;
    private BigDecimal precoPorUnidade;

    private LocalDateTime criadoEm;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public BigDecimal getPrecoPorUnidade() {
        return precoPorUnidade;
    }

    public void setPrecoPorUnidade(BigDecimal precoPorUnidade) {
        this.precoPorUnidade = precoPorUnidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public CreditoCarbono getCreditoCarbono() {
        return creditoCarbono;
    }

    public void setCreditoCarbono(CreditoCarbono creditoCarbono) {
        this.creditoCarbono = creditoCarbono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransacaoDeCredito that = (TransacaoDeCredito) o;
        return Objects.equals(id, that.id) && Objects.equals(creditoCarbono, that.creditoCarbono) && Objects.equals(comprador, that.comprador) && Objects.equals(vendedor, that.vendedor) && Objects.equals(quantidade, that.quantidade) && Objects.equals(precoPorUnidade, that.precoPorUnidade) && Objects.equals(criadoEm, that.criadoEm) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditoCarbono, comprador, vendedor, quantidade, precoPorUnidade, criadoEm, status);
    }
}
