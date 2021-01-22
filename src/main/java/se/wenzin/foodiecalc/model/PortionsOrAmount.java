package se.wenzin.foodiecalc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PORTIONSORAMOUNT")
public class PortionsOrAmount implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "MEASUREUNITID")
    private UUID measureUnitId;

    @Column(name = "QUANTITY")
    private Long quantity;

    public PortionsOrAmount() {
    }

    public PortionsOrAmount(UUID uuid, UUID measureUnitId, Long quantity) {
        this.uuid = uuid;
        this.measureUnitId = measureUnitId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getMeasureUnitId() {
        return measureUnitId;
    }

    public void setMeasureUnitId(UUID measureUnitId) {
        this.measureUnitId = measureUnitId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
