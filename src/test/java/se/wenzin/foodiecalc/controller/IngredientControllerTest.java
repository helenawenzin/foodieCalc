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

import static org.hamcrest.Matchers.containsString;

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
                .put("name", "vaniljsocker");

        Ingredient ingredient = createIngredient(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredient/" + ingredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getIngredients() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "vaniljsocker");
        Ingredient i1 = createIngredient(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "kakao");
        Ingredient i2 = createIngredient(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("vaniljsocker"))
                .and().body(containsString("kakao"))
                .and().body(containsString(i1.getId().toString()))
                .and().body(containsString(i2.getId().toString()));
    }

    @Test
    public void updateIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "bakpulver");
        Ingredient ingredient = createIngredient(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", ingredient.getId())
                .put("name", "bikarbonat");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/ingredient")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "socker");
        Ingredient ingredient = createIngredient(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/ingredient/" + ingredient.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private Ingredient createIngredient(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/ingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(Ingredient.class);
    }
}