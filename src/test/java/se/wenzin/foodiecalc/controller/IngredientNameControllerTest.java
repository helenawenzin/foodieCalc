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
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.model.IngredientName;

import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IngredientNameControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getIngredientNameById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "vaniljsocker");

        IngredientName ingredientName = createIngredientName(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredientname/" + ingredientName.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getIngredientNames() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "vaniljsocker");
        IngredientName i1 = createIngredientName(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "kakao");
        IngredientName i2 = createIngredientName(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredientnames")
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
    public void upgradeIngredientName() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "bakpulver");
        IngredientName ingredientName = createIngredientName(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", ingredientName.getId())
                .put("name", "bikarbonat");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/ingredientname")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteIngredientName() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "socker");
        IngredientName ingredientName = createIngredientName(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/ingredientname/" + ingredientName.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private IngredientName createIngredientName(String body) {

        System.out.println("..............." + body.toString());

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/ingredientname")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(IngredientName.class);
    }
}