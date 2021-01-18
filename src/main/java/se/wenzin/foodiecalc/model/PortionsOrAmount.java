package se.wenzin.foodiecalc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PORTIONSORAMOUNT")
public class PortionsOrAmount implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "UNITID")
    private UUID unitId;

    @Column(name = "QUANTITY")
    private Long quantity;

    public PortionsOrAmount() {}

    public PortionsOrAmount(UUID uuid1id, UUID unitId, Long quantity) {
        this.uuid = uuid;
        this.unitId = unitId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUnitId() {
        return unitId;
    }

    public void setUnitId(UUID unitId) {
        this.unitId = unitId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
