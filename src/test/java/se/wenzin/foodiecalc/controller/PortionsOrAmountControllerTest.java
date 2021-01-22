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
import se.wenzin.foodiecalc.model.PortionsOrAmount;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PortionsOrAmountControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getPortionsOrAmountById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("measureUnitId", UUID.randomUUID())
                .put("quantity", "20");

        PortionsOrAmount portionsOrAmount = createPortionsOrAmount(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/portionsoramount/" + portionsOrAmount.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPortionsOrAmounts() throws JSONException {
        JSONObject body = new JSONObject()
                .put("measureUnitId", UUID.randomUUID())
                .put("quantity", "4");
        PortionsOrAmount p1 = createPortionsOrAmount(body.toString());

        JSONObject body2 = new JSONObject()
                .put("measureUnitId", UUID.randomUUID())
                .put("quantity", "25");
        PortionsOrAmount p2 = createPortionsOrAmount(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/portionsoramounts")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString(body.getString("measureUnitId")))
                .and().body(containsString("25"))
                .and().body(containsString(p1.getId().toString()))
                .and().body(containsString(p2.getId().toString()));
    }

    @Test
    public void upgradePortionsOrAmount() throws JSONException {
        JSONObject body = new JSONObject()
                .put("measureUnitId", UUID.randomUUID())
                .put("quantity", "4");
        PortionsOrAmount portionsOrAmount = createPortionsOrAmount(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", portionsOrAmount.getId())
                .put("quantity", "10");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/portionsoramount")
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePortionsOrAmount() throws JSONException {
        JSONObject body = new JSONObject()
                .put("measureUnitId", UUID.randomUUID())
                .put("quantity", "4");
        PortionsOrAmount portionsOrAmount = createPortionsOrAmount(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/portionsoramount/" + portionsOrAmount.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private PortionsOrAmount createPortionsOrAmount(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/portionsoramount")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(PortionsOrAmount.class);
    }
}