package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import java.math.BigDecimal;
import java.util.UUID;

public class TestControllerUtil {

    public static IngredientDto createIngredient(JSONObject ingredient) {
        return RestAssured.given().contentType("application/json")
                .body(ingredient.toString())
                .post("/ingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(IngredientDto.class);
    }

    public static RecipeIngredientDto createRecipeIngredient(JSONObject recipeIngredient) {
        return RestAssured.given().contentType("application/json")
                .body(recipeIngredient.toString())
                .post("/recipeingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(RecipeIngredientDto.class);
    }

    public static RecipeDto createRecipe(JSONObject recipe) {
        return RestAssured.given().contentType("application/json")
                .body(recipe.toString())
                .post("/recipe")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(RecipeDto.class);
    }

    public static JSONObject createJsonIngredientBody(String name, BigDecimal purchasePrice, Long purchaseWeightOrQuantity, Long oneDeciliterWeight) throws JSONException {
        return new JSONObject()
                .put("name", name)
                .put("purchasePrice", purchasePrice)
                .put("purchaseWeightOrQuantity", purchaseWeightOrQuantity)
                .put("oneDeciliterWeight", oneDeciliterWeight);
    }

    public static JSONObject createJsonRecipeBody(String title, String portionsOrAmount, String instructions) throws JSONException {
        return new JSONObject()
                .put("title", title)
                .put("portionsOrAmount", portionsOrAmount)
                .put("instructions", instructions)
                .put("foodCategoryId", UUID.randomUUID());
    }

    public static JSONObject createJsonRecipeIngredientBody(UUID ingredientId, Long quantity, String measure, UUID recipeId) throws JSONException {
        return new JSONObject()
                .put("ingredientId", ingredientId)
                .put("measure", measure)
                .put("quantity", quantity)
                .put("recipeId", recipeId);

    }
}
