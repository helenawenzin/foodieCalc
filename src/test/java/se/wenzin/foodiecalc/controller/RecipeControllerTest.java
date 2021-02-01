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

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipe;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RecipeControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void createRecipeWithoutIngredients() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar",
                "20 st",
                "1.Blanda alla ingredienser. 2.Rulla bollar");

        Recipe recipe = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .get("/recipe/" + recipe.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("1.Blanda alla ingredienser. 2.Rulla bollar"))
                .and().body(containsString(recipe.getId().toString()));
    }

    private JSONObject createJsonRecipeBody(String title, String portionsOrAmount, String instructions) throws JSONException {

        return new JSONObject()
                .put("title", title)
                .put("portionsOrAmount", portionsOrAmount)
                .put("instructions", instructions)
                .put("foodCategoryId", UUID.randomUUID());
    }

    @Test
    public void getRecipeById() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kärleksmums",
                "20 st",
                "1.Blanda alla ingredienser. 2.Ät smeten.");

        Recipe recipe = createRecipe(recipeBody);

        JSONObject bodyForCreatingRecipeIngredient1 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 5);
        JSONObject bodyForCreatingRecipeIngredient2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 10);

        TestControllerUtil.createRecipeIngredient(recipe.getId(), bodyForCreatingRecipeIngredient1);
        TestControllerUtil.createRecipeIngredient(recipe.getId(), bodyForCreatingRecipeIngredient2);

        RestAssured.given().contentType("application/json")
                .get("/recipe/" + recipe.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("1.Blanda alla ingredienser. 2.Ät smeten."))
                .and().body(containsString(recipe.getId().toString()));
    }

    @Test
    public void getRecipes() throws JSONException {


        JSONObject recipeBody = createJsonRecipeBody("Köttbullar",
                "20 st",
                "1. Blanda alla ingredienser. 2. Stek");

        Recipe recipe1 = createRecipe(recipeBody);

        JSONObject bodyForCreatingRecipeIngredient1 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 1);
        JSONObject bodyForCreatingRecipeIngredient2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 1);

        TestControllerUtil.createRecipeIngredient(recipe1.getId(), bodyForCreatingRecipeIngredient1);
        TestControllerUtil.createRecipeIngredient(recipe1.getId(), bodyForCreatingRecipeIngredient2);


        JSONObject recipeBody2 = createJsonRecipeBody("Tacos",
                "8 st",
                "Stek köttfärs! Fyll tortillan!");

        Recipe recipe2 = createRecipe(recipeBody2);

        JSONObject bodyForCreatingRecipeIngredient1_1 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 3);
        JSONObject bodyForCreatingRecipeIngredient2_2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 3);

        TestControllerUtil.createRecipeIngredient(recipe2.getId(), bodyForCreatingRecipeIngredient1_1);
        TestControllerUtil.createRecipeIngredient(recipe2.getId(), bodyForCreatingRecipeIngredient2_2);

        RestAssured.given().contentType("application/json")
                .get("/recipes")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("Tacos"))
                .and().body(containsString("Köttbullar"))
                .and().body(containsString("1. Blanda alla ingredienser. 2. Stek"))
                .and().body(containsString("Stek köttfärs! Fyll tortillan!"))
                .and().body(containsString(recipe1.getId().toString()))
                .and().body(containsString(recipe2.getId().toString()));
    }

    @Test
    public void updateRecipe() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Tekakor",
                "18 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");

        Recipe recipe = createRecipe(recipeBody);

        JSONObject bodyForCreatingRecipeIngredient1 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 44);
        JSONObject bodyForCreatingRecipeIngredient2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 45);

        TestControllerUtil.createRecipeIngredient(recipe.getId(), bodyForCreatingRecipeIngredient1);
        TestControllerUtil.createRecipeIngredient(recipe.getId(), bodyForCreatingRecipeIngredient2);

        JSONObject updateBody = new JSONObject()
                .put("id", recipe.getId())
                .put("instructions", "Gör om, gör rätt!");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/recipe")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("Gör om, gör rätt!"))
                .and().body(containsString("Tekakor"));
    }

    @Test
    public void deleteRecipeWithoutIngredients() throws JSONException {

        JSONObject recipeBody = new JSONObject()
                .put("title", "Muffins")
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("instructions", "1. Blanda alla ingredienser. 2. In i ugnen ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .delete("/recipe/" + recipe.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}