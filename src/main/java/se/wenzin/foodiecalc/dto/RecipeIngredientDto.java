package se.wenzin.foodiecalc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDto {

    private UUID id;
    private UUID ingredientId;
    private String measure;
    private Long quantity;
    private UUID recipeId;
    private BigDecimal cost;
}
