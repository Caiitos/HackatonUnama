package ecotrade.example.projeto.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CreditoCarbono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario producer;

    private String origin;
    private LocalDate generationDate;

    // Corrigido o nome do atributo
    private BigDecimal availableQuantity;

    private boolean active = true;

    @Version
    private Long version;

    public CreditoCarbono() {
    }

    public CreditoCarbono(Long id, Usuario producer, String origin,
                          LocalDate generationDate, BigDecimal availableQuantity, Long version) {
        this.id = id;
        this.producer = producer;
        this.origin = origin;
        this.generationDate = generationDate;
        this.availableQuantity = availableQuantity;
        this.version = version;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getProducer() {
        return producer;
    }

    public void setProducer(Usuario producer) {
        this.producer = producer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    // Métodos utilitários
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditoCarbono)) return false;
        CreditoCarbono that = (CreditoCarbono) o;
        return active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(producer, that.producer) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(generationDate, that.generationDate) &&
                Objects.equals(availableQuantity, that.availableQuantity) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, origin, generationDate, availableQuantity, active, version);
    }
}
