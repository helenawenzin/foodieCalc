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
@Table(name = "RECIPEINGREDIENT")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "INGREDIENTID")
    private UUID ingredientId;

    @Column(name = "MEASUREID")
    private UUID measureId;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "RECIPEID")
    private UUID recipeId;

    public RecipeIngredient() {
    }

    public RecipeIngredient(UUID uuid, UUID ingredientId, UUID measureId, Long quantity, UUID recipeId) {
        this.uuid = uuid;
        this.ingredientId = ingredientId;
        this.measureId = measureId;
        this.quantity = quantity;
        this.recipeId = recipeId;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(UUID ingredientId) {
        this.ingredientId = ingredientId;
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

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "uuid=" + uuid +
                ", ingredientId=" + ingredientId +
                ", measureId=" + measureId +
                ", quantity=" + quantity +
                ", recipeId=" + recipeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(ingredientId, that.ingredientId) &&
                Objects.equals(measureId, that.measureId) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(recipeId, that.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, ingredientId, measureId, quantity, recipeId);
    }
}
