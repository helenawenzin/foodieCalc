package se.wenzin.foodiecalc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "PORTIONSORAMOUNT")
    private String portionsOrAmount;

    @ManyToMany
    @Column(name = "INGREDIENTS")
    private Set<Ingredient> ingredients;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "FOODCATEGORYID")
    private UUID foodCategoryId;

    public Recipe() {
    }

    public Recipe(UUID uuid, String portionsOrAmount, Set<Ingredient> ingredients, String instructions, UUID foodCategoryId) {
        this.uuid = uuid;
        this.portionsOrAmount = portionsOrAmount;
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

    public String getPortionsOrAmount() {
        return portionsOrAmount;
    }

    public void setPortionsOrAmountId(String portionsOrAmount) {
        this.portionsOrAmount = portionsOrAmount;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
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

    @Override
    public String toString() {
        return "Recipe{" +
                "uuid=" + uuid +
                ", portionsOrAmount='" + portionsOrAmount + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", foodCategoryId=" + foodCategoryId +
                '}';
    }
}
