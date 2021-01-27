package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.json.JSONObject;
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.model.Recipe;

public class TestControllerUtil {

    public static Ingredient createIngredient(JSONObject ingredient) {
        return RestAssured.given().contentType("application/json")
                .body(ingredient.toString())
                .post("/ingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(Ingredient.class);
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
}
