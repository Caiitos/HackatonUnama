package entities;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditoCarbono {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @ManyToOne(optional = false)
   private User producer;

   private String origin;
   private LocalDate generationDate;

    private BigDecimal availableQauntity;

    private boolean active = true;

    @Version
    private Long version;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
