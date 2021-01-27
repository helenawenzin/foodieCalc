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
import se.wenzin.foodiecalc.model.Ingredient;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createIngredient;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IngredientControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getIngredientById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");

        Ingredient ingredient = createIngredient(body);

        RestAssured.given().contentType("application/json")
                .get("/ingredient/" + ingredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getIngredients() throws JSONException {
        JSONObject body = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");
        Ingredient i1 = createIngredient(body);

        JSONObject body2 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "4");
        Ingredient i2 = createIngredient(body2);

        RestAssured.given().contentType("application/json")
                .get("/ingredients")
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
    public void updateIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "2");
        Ingredient ingredient = createIngredient(body);

        JSONObject updateBody = new JSONObject()
                .put("id", ingredient.getId())
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", "5");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/ingredient")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");
        Ingredient ingredient = createIngredient(body);

        RestAssured.given().contentType("application/json")
                .delete("/ingredient/" + ingredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

}