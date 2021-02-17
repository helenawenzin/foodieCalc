package se.wenzin.foodiecalc.service;

import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecipeIngredientCostCalculator {

    public static BigDecimal calculate(RecipeIngredientDto recipeIngredientDto, IngredientDto ingredientDto) {

        Long weightInDl = ingredientDto.getOneDeciliterWeight();
        Long weightOrQuantityForThisIngredient = switch (recipeIngredientDto.getMeasure()) {
            case "l" -> weightInDl * 10;
            case "dl" -> weightInDl;
            case "msk" -> weightInDl / 6;
            case "tsk" -> weightInDl / 20;
            case "krm", "nypa" -> weightInDl / 60;
            case "gr", "st" -> 1L;
            default -> throw new IllegalStateException("Unexpected value: " + recipeIngredientDto.getMeasure());
        };

        BigDecimal purchasePrice = ingredientDto.getPurchasePrice();
        Long quantity = recipeIngredientDto.getQuantity();

        return purchasePrice
                .divide(new BigDecimal(ingredientDto.getPurchaseWeightOrQuantity()))
                .multiply(new BigDecimal(quantity * weightOrQuantityForThisIngredient)).setScale(2, RoundingMode.HALF_UP);
    }
}
