package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;

import java.util.UUID;

public class TestControllerUtil {

    public static RecipeIngredient createRecipeIngredient(UUID recipeId, JSONObject recipeIngredient) {
        return RestAssured.given().contentType("application/json")
                .body(recipeIngredient.toString())
                .pathParam("recipeId", recipeId)
                .post("/{recipeId}/recipeingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(RecipeIngredient.class);
    }

    public static Recipe createRecipe(JSONObject recipe) {
        return RestAssured.given().contentType("application/json")
                .body(recipe.toString())
                .post("/recipe")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(Recipe.class);
    }

    public static JSONObject createJsonRecipeBody(String title, String portionsOrAmount, String instructions) throws JSONException {
        return new JSONObject()
                .put("title", title)
                .put("portionsOrAmount", portionsOrAmount)
                .put("instructions", instructions)
                .put("foodCategoryId", UUID.randomUUID());
    }

    public static JSONObject createJsonRecipeIngredientBody(Long quantity, String measure) throws JSONException {
        return new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measure", measure)
                .put("quantity", quantity);

    }
}
