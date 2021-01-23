package se.wenzin.foodiecalc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "INGREDIENTNAMEID")
    private UUID ingredientNameId;

    @Column(name = "MEASUREID")
    private UUID measureId;

    @Column(name = "QUANTITY")
    private Long quantity;

    public Ingredient() {}

    public Ingredient(UUID uuid, UUID ingredientNameId, UUID measureId, UUID quantityId) {
        this.uuid = uuid;
        this.ingredientNameId = ingredientNameId;
        this.measureId = measureId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getIngredientNameId() {
        return ingredientNameId;
    }

    public void setIngredientNameId(UUID ingredientNameId) {
        this.ingredientNameId = ingredientNameId;
    }

    public UUID getMeasureId() {
        return measureId;
    }

    public void setMeasureId(UUID measureId) {
        this.measureId = measureId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
