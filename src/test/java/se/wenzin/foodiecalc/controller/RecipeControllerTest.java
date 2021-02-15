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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.wenzin.foodiecalc.dto.RecipeDto;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createJsonRecipeBody;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createJsonRecipeIngredientBody;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipe;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipeIngredient;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RecipeControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void createRecipeWithoutIngredients() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar", "20 st",
                "1.Blanda alla ingredienser. 2.Rulla bollar");

        RecipeDto recipeDto = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .get("/recipe/" + recipeDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("1.Blanda alla ingredienser. 2.Rulla bollar"))
                .and().body(containsString(recipeDto.getId().toString()));
    }

    @Test
    public void getRecipeById() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kärleksmums", "20 st",
                "1.Blanda alla ingredienser. 2.Ät smeten.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientBody1 = createJsonRecipeIngredientBody(5L, "msk", recipeDto.getId());
        JSONObject recipeIngredientBody2 = createJsonRecipeIngredientBody(10L, "tsk", recipeDto.getId());

        createRecipeIngredient(recipeDto.getId(), recipeIngredientBody1);
        createRecipeIngredient(recipeDto.getId(), recipeIngredientBody2);

        RestAssured.given().contentType("application/json")
                .get("/recipe/" + recipeDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("1.Blanda alla ingredienser. 2.Ät smeten."))
                .and().body(containsString(recipeDto.getId().toString()));
    }

    @Test
    public void getRecipes() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Köttbullar", "20 st",
                "1. Blanda alla ingredienser. 2. Stek");
        RecipeDto recipeDto1 = createRecipe(recipeBody);

        JSONObject recipeIngredientBody1 = createJsonRecipeIngredientBody(1L, "tsk", recipeDto1.getId());
        JSONObject recipeIngredientBody2 = createJsonRecipeIngredientBody(5L, "msk", recipeDto1.getId());
        createRecipeIngredient(recipeDto1.getId(), recipeIngredientBody1);
        createRecipeIngredient(recipeDto1.getId(), recipeIngredientBody2);

        JSONObject recipeBody2 = createJsonRecipeBody("Tacos", "8 st",
                "Stek köttfärs! Fyll tortillan!");
        RecipeDto recipeDto2 = createRecipe(recipeBody2);

        JSONObject recipeIngredientBody1_1 = createJsonRecipeIngredientBody(3L, "liter", recipeDto2.getId());
        JSONObject recipeIngredientBody2_2 = createJsonRecipeIngredientBody(6L, "krm", recipeDto2.getId());
        createRecipeIngredient(recipeDto2.getId(), recipeIngredientBody1_1);
        createRecipeIngredient(recipeDto2.getId(), recipeIngredientBody2_2);

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
                .and().body(containsString(recipeDto1.getId().toString()))
                .and().body(containsString(recipeDto2.getId().toString()));
    }

    @Test
    public void updateRecipe() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Tekakor", "18 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientBody1 = createJsonRecipeIngredientBody(44L, "msk", recipeDto.getId());
        JSONObject recipeIngredientBody2 = createJsonRecipeIngredientBody(45L, "tsk", recipeDto.getId());

        createRecipeIngredient(recipeDto.getId(), recipeIngredientBody1);
        createRecipeIngredient(recipeDto.getId(), recipeIngredientBody2);

        JSONObject updateBody = new JSONObject()
                .put("id", recipeDto.getId())
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

        JSONObject recipeBody = createJsonRecipeBody("Muffins", "15 st",
                "1. Vispa ihop ingredienserna. 2.In i ugnen");

        RecipeDto recipeDto = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .delete("/recipe/" + recipeDto.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}