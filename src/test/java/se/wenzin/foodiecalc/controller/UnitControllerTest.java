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
import se.wenzin.foodiecalc.model.Unit;

import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UnitControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getUnitById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "dl");

        Unit unit = createUnit(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/unit/" + unit.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getUnits() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "dl");
        Unit u1 = createUnit(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "msk");
        Unit u2 = createUnit(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/units")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("dl"))
                .and().body(containsString("msk"))
                .and().body(containsString(u1.getId().toString()))
                .and().body(containsString(u2.getId().toString()));
    }

    @Test
    public void upgradeUnit() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "dl");
        Unit unit = createUnit(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", unit.getId())
                .put("name", "tsk");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/unit")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteFoodCategory() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "liter");
        Unit unit = createUnit(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/unit/" + unit.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private Unit createUnit(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/unit")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(Unit.class);
    }
}