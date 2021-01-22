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
import se.wenzin.foodiecalc.model.MeasureQuantity;

import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MeasureQuantityControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getFoodCategoryById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("quantity", "5");

        MeasureQuantity measureQuantity = createMeasureQuantity(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/measurequantity/" + measureQuantity.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getMeasureQuantities() throws JSONException {
        JSONObject body = new JSONObject()
                .put("quantity", "2");
        MeasureQuantity m1 = createMeasureQuantity(body.toString());

        JSONObject body2 = new JSONObject()
                .put("quantity", "5");
        MeasureQuantity m2 = createMeasureQuantity(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/measurequantities")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("2"))
                .and().body(containsString("5"))
                .and().body(containsString(m1.getId().toString()))
                .and().body(containsString(m2.getId().toString()));
    }

    @Test
    public void upgradeMeasureQuantity() throws JSONException {
        JSONObject body = new JSONObject()
                .put("quantity", "3");
        MeasureQuantity measureQuantity = createMeasureQuantity(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", measureQuantity.getId())
                .put("quantity", "4");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/measurequantity")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMeasureQuantity() throws JSONException {
        JSONObject body = new JSONObject()
                .put("quantity", "1");
        MeasureQuantity measureQuantity = createMeasureQuantity(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/measurequantity/" + measureQuantity.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private MeasureQuantity createMeasureQuantity(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/measurequantity")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(MeasureQuantity.class);
    }
}