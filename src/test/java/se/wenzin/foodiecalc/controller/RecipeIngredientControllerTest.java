package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
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
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createIngredient;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createJsonIngredientBody;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createJsonRecipeBody;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createJsonRecipeIngredientBody;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipe;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipeIngredient;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RecipeIngredientControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }


    @Test
    public void getRecipeIngredientByIdWithPurchaseWeight() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar", "20 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody = createJsonIngredientBody("Havregryn", BigDecimal.valueOf(20), 100L,
                null, 35L);
        IngredientDto ingredientDto = createIngredient(ingredientBody);

        JSONObject recipeIngredientBody = createJsonRecipeIngredientBody(ingredientDto.getId(), 2L, "dl", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeIngredientBody);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .body("cost", CoreMatchers.is(14f));
    }

    @Test
    public void getRecipeIngredientByIdWithPurchaseQuantity() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar", "20 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody = createJsonIngredientBody("Ägg", BigDecimal.valueOf(40), null,
                20L, null);
        IngredientDto ingredientDto = createIngredient(ingredientBody);

        JSONObject recipeIngredientBody = createJsonRecipeIngredientBody(ingredientDto.getId(), 2L, null, recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeIngredientBody);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .body("cost", CoreMatchers.is(4f));
    }

    @Test
    public void getRecipeIngredientByIdWithGram() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar", "20 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody = createJsonIngredientBody("Smör", BigDecimal.valueOf(30), 500L,
                null, null);
        IngredientDto ingredientDto = createIngredient(ingredientBody);

        JSONObject recipeIngredientBody = createJsonRecipeIngredientBody(ingredientDto.getId(), 200L, "gr", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeIngredientBody);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .body("cost", CoreMatchers.is(12f));
    }

    @Test
    public void getRecipeIngredients() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kokostoppar", "40 st",
                "1. Blanda. 2.Klicka ut.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody1 = createJsonIngredientBody("Ägg", BigDecimal.valueOf(40), null,
                20L, null);
        IngredientDto ingredientDto = createIngredient(ingredientBody1);

        JSONObject recipeIngredientBody = createJsonRecipeIngredientBody(ingredientDto.getId(), 11L, null, recipeDto.getId());
        createRecipeIngredient(recipeIngredientBody);

        JSONObject recipeBody2 = createJsonRecipeBody("Järpar", "22 st",
                "1. Platta till. 2.Stek");
        RecipeDto recipeDto2 = createRecipe(recipeBody2);

        JSONObject ingredientBody2 = createJsonIngredientBody("Vetemjöl", BigDecimal.valueOf(40), 1000L,
                null, 65L);
        IngredientDto ingredientDto2 = createIngredient(ingredientBody2);

        JSONObject recipeIngredientBody2 = createJsonRecipeIngredientBody(ingredientDto2.getId(), 22L, "dl", recipeDto2.getId());
        createRecipeIngredient(recipeIngredientBody2);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("11"))
                .body(containsString("22"));
    }

    @Test
    public void updateRecipeIngredient() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kokostoppar", "40 st",
                "1. Blanda. 2.Klicka ut.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody = createJsonIngredientBody("Ägg", BigDecimal.valueOf(40), null,
                20L, null);
        IngredientDto ingredientDto = createIngredient(ingredientBody);

        JSONObject recipeIngredientBody = createJsonRecipeIngredientBody(ingredientDto.getId(), 11L, null, recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeIngredientBody);

        JSONObject updateBody = new JSONObject()
                .put("id", recipeIngredientDto.getId())
                .put("quantity", "50");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/recipeingredient")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("50"));
    }

    @Test
    public void deleteRecipeIngredient() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Amarulaparfait", "1 st",
                "1. Blanda. 2.Fryyyys!");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject ingredientBody = createJsonIngredientBody("Ägg", BigDecimal.valueOf(40), null,
                20L, null);
        IngredientDto ingredientDto = createIngredient(ingredientBody);

        JSONObject recipeIngredientbody = createJsonRecipeIngredientBody(ingredientDto.getId(), 3L, null, recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeIngredientbody);

        RestAssured.given().contentType("application/json")
                .delete("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}