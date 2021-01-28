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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PORTIONSORAMOUNT")
    private String portionsOrAmount;

    @ManyToMany
    @Column(name = "INGREDIENTS")
    private Set<RecipeIngredient> recipeIngredients;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "FOODCATEGORYID")
    private UUID foodCategoryId;

    public Recipe() {
    }

    public Recipe(UUID uuid, String title, String portionsOrAmount, Set<RecipeIngredient> recipeIngredients, String instructions, UUID foodCategoryId) {
        this.uuid = uuid;
        this.title = title;
        this.portionsOrAmount = portionsOrAmount;
        this.recipeIngredients = recipeIngredients;
        this.instructions = instructions;
        this.foodCategoryId = foodCategoryId;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPortionsOrAmount() {
        return portionsOrAmount;
    }

    public void setPortionsOrAmount(String portionsOrAmount) {
        this.portionsOrAmount = portionsOrAmount;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
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
                ", title='" + title + '\'' +
                ", portionsOrAmount='" + portionsOrAmount + '\'' +
                ", ingredients=" + recipeIngredients +
                ", instructions='" + instructions + '\'' +
                ", foodCategoryId=" + foodCategoryId +
                '}';
    }
}
