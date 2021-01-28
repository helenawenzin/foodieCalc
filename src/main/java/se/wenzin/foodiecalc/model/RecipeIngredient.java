package se.wenzin.foodiecalc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "INGREDIENTNAMEID")
    private UUID ingredientNameId;

    @Column(name = "MEASUREID")
    private UUID measureId;

    @Column(name = "QUANTITY")
    private Long quantity;

    public RecipeIngredient() {
    }

    public RecipeIngredient(UUID uuid, UUID ingredientNameId, UUID measureId, UUID quantityId) {
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "uuid=" + uuid +
                ", ingredientNameId=" + ingredientNameId +
                ", measureId=" + measureId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return uuid.equals(that.uuid) &&
                ingredientNameId.equals(that.ingredientNameId) &&
                measureId.equals(that.measureId) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, ingredientNameId, measureId, quantity);
    }
}
