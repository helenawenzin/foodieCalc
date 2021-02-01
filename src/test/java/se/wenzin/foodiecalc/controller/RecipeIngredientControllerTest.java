package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipe;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipeIngredient;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RecipeIngredientControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getRecipeIngredientById() throws JSONException {

        JSONObject recipeBody = new JSONObject()
                .put("title", "Chokladbollar")
                .put("portionsOrAmount", "20 st")
                .put("instructions", "1. Blanda alla ingredienser. 2.Rulla bollar.")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");

        RecipeIngredient recipeIngredient = createRecipeIngredient(recipe.getId(), body);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getRecipeIngredients() throws JSONException {

        JSONObject recipeBody = new JSONObject()
                .put("title", "Kokostoppar")
                .put("portionsOrAmount", "40 st")
                .put("instructions", "1. Blanda. 2.Klicka ut.")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "11");
        //.put("recipeId", UUID.randomUUID());

        RecipeIngredient i1 = createRecipeIngredient(recipe.getId(), body);

        JSONObject recipeBody2 = new JSONObject()
                .put("title", "Järpar")
                .put("portionsOrAmount", "22st")
                .put("instructions", "1. Platta till. 2.Stek")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe2 = createRecipe(recipeBody2);

        JSONObject body2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "22");

        RecipeIngredient i2 = createRecipeIngredient(recipe2.getId(), body2);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("22"))
                .and().body(containsString("11"))
                .and().body(containsString(i1.getId().toString()))
                .and().body(containsString(i2.getId().toString()));
    }

    @Test
    public void updateRecipeIngredient() throws JSONException {

        JSONObject recipeBody = new JSONObject()
                .put("title", "Kokostoppar")
                .put("portionsOrAmount", "40 st")
                .put("instructions", "1. Blanda. 2.Klicka ut.")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "11");

        RecipeIngredient i1 = createRecipeIngredient(recipe.getId(), body);

        JSONObject updateBody = new JSONObject()
                .put("id", i1.getId())
                .put("quantity", "50");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put(recipe.getId() + "/recipeingredient")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteRecipeIngredient() throws JSONException {
        JSONObject recipeBody = new JSONObject()
                .put("title", "Amarulaparfait")
                .put("portionsOrAmount", "1 st")
                .put("instructions", "1. Blanda. 2.Fryyyys!")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "3");

        RecipeIngredient i1 = createRecipeIngredient(recipe.getId(), body);

        RestAssured.given().contentType("application/json")
                .delete("/recipeingredient/" + i1.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}