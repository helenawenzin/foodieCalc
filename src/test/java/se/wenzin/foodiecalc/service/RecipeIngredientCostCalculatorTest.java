package se.wenzin.foodiecalc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import java.math.BigDecimal;
import java.util.stream.Stream;

class RecipeIngredientCostCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideArgumentsForCalculateCost")
    public void calculateRecipeIngredientCosts(String measure, Long quantity, BigDecimal purchasePrice,
                                               Long purchaseWeightOrQuantity, Long oneDeciliterWeight, BigDecimal expected) {

        RecipeIngredientDto recipeIngredientDto = RecipeIngredientDto.builder()
                .measure(measure)
                .quantity(quantity)
                .build();
        IngredientDto ingredientDto = IngredientDto.builder()
                .purchasePrice(purchasePrice)
                .purchaseWeightOrQuantity(purchaseWeightOrQuantity)
                .oneDeciliterWeight(oneDeciliterWeight)
                .build();

        BigDecimal calculate = RecipeIngredientCostCalculator.calculate(recipeIngredientDto, ingredientDto);

        Assertions.assertEquals(expected, calculate);
    }

    private static Stream<Arguments> provideArgumentsForCalculateCost() {

        return Stream.of(
                Arguments.of("gr", 200L, new BigDecimal("30.00"), 500L, null, new BigDecimal("12.00")),
                Arguments.of("st", 2L, new BigDecimal("40.00"), 20L, null, new BigDecimal("4.00")),
                Arguments.of("dl", 4L, new BigDecimal("10.00"), 2000L, 60L, new BigDecimal("1.20")),
                Arguments.of("l", 1L, new BigDecimal("14.00"), 1000L, 100L, new BigDecimal("14.00")),
                Arguments.of("msk", 4L, new BigDecimal("44.00"), 400L, 40L, new BigDecimal("2.64")),
                Arguments.of("tsk", 2L, new BigDecimal("9.00"), 1000L, 125L, new BigDecimal("0.11")),
                Arguments.of("krm", 1L, new BigDecimal("9.00"), 1000L, 125L, new BigDecimal("0.02"))
                //Arguments.of("nypa", 3L, new BigDecimal("9.00"), 1000L, 125L, new BigDecimal("0.06"))
        );

    }
}

