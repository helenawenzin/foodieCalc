package se.wenzin.foodiecalc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import java.math.BigDecimal;

class RecipeIngredientCostCalculatorTest {

    // TODO: Try parameterized test!

    @Test
    public void calculateButterCost() {
        RecipeIngredientDto recipeIngredientDto = RecipeIngredientDto.builder()
                .measure("gr")
                .quantity(200L)
                .build();
        IngredientDto ingredientDto = IngredientDto.builder()
                .purchasePrice(new BigDecimal("30.00"))
                .purchaseWeightOrQuantity(500L)
                .build();

        BigDecimal calculate = RecipeIngredientCostCalculator.calculate(recipeIngredientDto, ingredientDto);

        Assertions.assertEquals(new BigDecimal("12.00"), calculate);
    }

    //public void calculateButterCost(String measure, Long quantity, ..., BigDecimal expected) {}
}
