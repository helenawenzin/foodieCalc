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
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;

import static org.hamcrest.Matchers.containsString;
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
    public void getRecipeIngredientById() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Chokladbollar", "20 st",
                "1. Blanda alla ingredienser. 2.Rulla bollar.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientbody = createJsonRecipeIngredientBody(2L, "msk", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeDto.getId(), recipeIngredientbody);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getRecipeIngredients() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kokostoppar", "40 st",
                "1. Blanda. 2.Klicka ut.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientbody = createJsonRecipeIngredientBody(11L, "liter", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto1 = createRecipeIngredient(recipeDto.getId(), recipeIngredientbody);

        JSONObject recipeBody2 = createJsonRecipeBody("Järpar", "22 st",
                "1. Platta till. 2.Stek");
        RecipeDto recipeDto2 = createRecipe(recipeBody2);

        JSONObject recipeIngredientbody2 = createJsonRecipeIngredientBody(22L, "tsk", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto2 = createRecipeIngredient(recipeDto2.getId(), recipeIngredientbody2);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("22"))
                .and().body(containsString("11"))
                .and().body(containsString(recipeIngredientDto1.getId().toString()))
                .and().body(containsString(recipeIngredientDto2.getId().toString()));
    }

    @Test
    public void updateRecipeIngredient() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Kokostoppar", "40 st",
                "1. Blanda. 2.Klicka ut.");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientbody = createJsonRecipeIngredientBody(11L, "krm", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeDto.getId(), recipeIngredientbody);

        JSONObject updateBody = new JSONObject()
                .put("id", recipeIngredientDto.getId())
                .put("quantity", "50");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put(recipeDto.getId() + "/recipeingredient")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteRecipeIngredient() throws JSONException {

        JSONObject recipeBody = createJsonRecipeBody("Amarulaparfait", "1 st",
                "1. Blanda. 2.Fryyyys!");
        RecipeDto recipeDto = createRecipe(recipeBody);

        JSONObject recipeIngredientbody = createJsonRecipeIngredientBody(3L, "dl", recipeDto.getId());
        RecipeIngredientDto recipeIngredientDto = createRecipeIngredient(recipeDto.getId(), recipeIngredientbody);

        RestAssured.given().contentType("application/json")
                .delete("/recipeingredient/" + recipeIngredientDto.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}