package model;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CreditoCarbono {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(optional = false)
   private User producer;

   private String origin;
   private LocalDate generationDate;

   private BigDecimal availableQauntity;

   private boolean active = true;

    @Version
    private Long version;

    public CreditoCarbono(){

    }

    public CreditoCarbono(Long id, User producer, String origin, LocalDate generationDate, BigDecimal availableQauntity, Long version) {

        this.id = id;
        this.producer = producer;
        this.origin = origin;
        this.generationDate = generationDate;
        this.availableQauntity = availableQauntity;
        this.version = version;

    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getAvailableQauntity() {
        return availableQauntity;
    }

    public void setAvailableQauntity(BigDecimal availableQauntity) {
        this.availableQauntity = availableQauntity;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public User getProducer() {
        return producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditoCarbono that = (CreditoCarbono) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(producer, that.producer) && Objects.equals(origin, that.origin) && Objects.equals(generationDate, that.generationDate) && Objects.equals(availableQauntity, that.availableQauntity) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, origin, generationDate, availableQauntity, active, version);
    }
}
