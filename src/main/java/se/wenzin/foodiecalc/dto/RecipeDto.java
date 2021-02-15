package se.wenzin.foodiecalc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RecipeDto {

    private UUID id;
    private String title;
    private String portionsOrAmount;
    private String instructions;
    private UUID foodCategoryId;
    private Set<RecipeIngredientDto> recipeIngredients;
}
