package se.wenzin.foodiecalc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RecipeIngredientDto {

    private UUID id;
    private UUID ingredientId;
    private String measure;
    private Long quantity;
    private UUID recipeId;
    private BigDecimal cost;
}
