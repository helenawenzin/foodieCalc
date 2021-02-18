package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
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

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

        IngredientDto ingredientDto = createIngredient(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredient/" + ingredientDto.getId())
                .then()
                .log().all()
                .statusCode(200)
                .body(containsString("vaniljsocker"));;
    }

    @Test
    public void getIngredientByIdNotFound() throws JSONException {

        UUID id = UUID.randomUUID();
        RestAssured.given().contentType("application/json")
                .get("/ingredient/" + id)
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void getIngredientsEmpty() {
        RestAssured.given().contentType("application/json")
                .get("/ingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("isEmpty()", Matchers.is(true));
    }


    @Test
    public void getIngredients() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "vaniljsocker");
        IngredientDto i1 = createIngredient(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "kakao");
        IngredientDto i2 = createIngredient(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/ingredients")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("size()", is(2))
                .body(containsString("vaniljsocker"))
                .and().body(containsString("kakao"))
                .and().body(containsString(i1.getId().toString()))
                .and().body(containsString(i2.getId().toString()));
    }

    @Test
    public void updateIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "bakpulver");
        IngredientDto ingredientDto = createIngredient(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", ingredientDto.getId())
                .put("name", "bikarbonat");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/ingredient")
                .then()
                .statusCode(200)
                .assertThat()
                .body(containsString("bikarbonat"));;
    }

    @Test
    public void deleteIngredient() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "socker");
        IngredientDto ingredientDto = createIngredient(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/ingredient/" + ingredientDto.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private IngredientDto createIngredient(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/ingredient")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(IngredientDto.class);
    }
}