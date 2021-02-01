package se.wenzin.foodiecalc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "RECIPEINGREDIENT")
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "INGREDIENTID")
    private UUID ingredientId;

    @Column(name = "MEASUREID")
    private UUID measureId;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(UUID id, UUID ingredientId, UUID measureId, Long quantity, Recipe recipe) {
        this.id = id;
        this.ingredientId = ingredientId;
        this.measureId = measureId;
        this.quantity = quantity;
        this.recipe = recipe;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "uuid=" + id +
                ", ingredientId=" + ingredientId +
                ", measureId=" + measureId +
                ", quantity=" + quantity +
                ", recipe=" + recipe +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ingredientId, that.ingredientId) &&
                Objects.equals(measureId, that.measureId) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredientId, measureId, quantity, recipe);
    }
}
