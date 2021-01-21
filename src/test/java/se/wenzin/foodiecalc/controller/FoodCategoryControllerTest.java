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

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FoodCategoryControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getFoodCategory() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");

        FoodCategory foodCategory = createFoodCategory(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/foodcategory/" + foodCategory.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void getFoodcategories() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");
        createFoodCategory(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "lunch");
        createFoodCategory(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/foodcategories")
                .then()
                .log().all()
                .statusCode(200)
                .body("[0]", hasKey("id"))
                .assertThat().body(notNullValue());
    }

    @Test
    public void upgradeFoodCategory() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");
        FoodCategory foodCategory = createFoodCategory(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", foodCategory.getId())
                .put("name", "lunch");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/foodcategory")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteFoodCategory() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "breakfast");
        FoodCategory foodCategory = createFoodCategory(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/foodcategory/" + foodCategory.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private FoodCategory createFoodCategory(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/foodcategory")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(FoodCategory.class);
    }
}