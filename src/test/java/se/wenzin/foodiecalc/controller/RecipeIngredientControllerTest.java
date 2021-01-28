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
import se.wenzin.foodiecalc.model.RecipeIngredient;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
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
        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");

        RecipeIngredient recipeIngredient = createRecipeIngredient(body);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredient/" + recipeIngredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getRecipeIngredients() throws JSONException {
        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");
        RecipeIngredient i1 = createRecipeIngredient(body);

        JSONObject body2 = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "4");
        RecipeIngredient i2 = createRecipeIngredient(body2);

        RestAssured.given().contentType("application/json")
                .get("/recipeingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("2"))
                .and().body(containsString("4"))
                .and().body(containsString(i1.getId().toString()))
                .and().body(containsString(i2.getId().toString()));
    }

    @Test
    public void updateRecipeIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");
        RecipeIngredient recipeIngredient = createRecipeIngredient(body);

        JSONObject updateBody = new JSONObject()
                .put("id", recipeIngredient.getId())
                .put("ingredientId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "5");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/recipeingredient")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");
        RecipeIngredient recipeIngredient = createRecipeIngredient(body);

        RestAssured.given().contentType("application/json")
                .delete("/recipeingredient/" + recipeIngredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

}