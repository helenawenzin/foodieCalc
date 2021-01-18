package se.wenzin.foodiecalc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "PORTIONSORAMOUNTID")
    private UUID portionsOrAmountId;

    @ElementCollection
    @Column(name = "INGREDIENTS")
    private List<Ingredient> ingredients;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "FOODCATEGORYID")
    private UUID foodCategoryId;

    public Recipe() {
    }

    public Recipe(UUID uuid, UUID portionsOrAmountId, List<Ingredient> ingredients, String instructions, UUID foodCategoryId) {
        this.uuid = uuid;
        this.portionsOrAmountId = portionsOrAmountId;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.foodCategoryId = foodCategoryId;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getPortionsOrAmountId() {
        return portionsOrAmountId;
    }

    public void setPortionsOrAmountId(UUID portionsOrAmountId) {
        this.portionsOrAmountId = portionsOrAmountId;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public UUID getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(UUID foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }
}
