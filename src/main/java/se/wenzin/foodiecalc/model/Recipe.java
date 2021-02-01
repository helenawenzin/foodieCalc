package se.wenzin.foodiecalc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "RECIPE")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PORTIONSORAMOUNT")
    private String portionsOrAmount;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "FOODCATEGORYID")
    private UUID foodCategoryId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;

    public Recipe() {
    }

    public Recipe(UUID id, String title, String portionsOrAmount, String instructions, UUID foodCategoryId, Set<RecipeIngredient> recipeIngredients) {
        this.id = id;
        this.title = title;
        this.portionsOrAmount = portionsOrAmount;
        this.instructions = instructions;
        this.foodCategoryId = foodCategoryId;
        this.recipeIngredients = recipeIngredients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + id +
                ", title='" + title + '\'' +
                ", portionsOrAmount='" + portionsOrAmount + '\'' +
                ", instructions='" + instructions + '\'' +
                ", foodCategoryId=" + foodCategoryId +
                ", recipeIngredients=" + recipeIngredients +
                '}';
    }
}
