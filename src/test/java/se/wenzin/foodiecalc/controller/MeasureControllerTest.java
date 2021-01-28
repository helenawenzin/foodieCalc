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
import se.wenzin.foodiecalc.model.Measure;

import static org.hamcrest.Matchers.containsString;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MeasureControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getMeasureById() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "portioner");

        Measure measure = createMeasure(body.toString());

        RestAssured.given().contentType("application/json")
                .get("/measure/" + measure.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("portioner"));
    }

    @Test
    public void getMeasures() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "portioner");
        Measure measure1 = createMeasure(body.toString());

        JSONObject body2 = new JSONObject()
                .put("name", "nypa");
        Measure measure2 = createMeasure(body2.toString());

        RestAssured.given().contentType("application/json")
                .get("/measures")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("nypa"))
                .and().body(containsString("portioner"))
                .and().body(containsString(measure1.getId().toString()))
                .and().body(containsString(measure2.getId().toString()));
    }

    @Test
    public void upgradeMeasure() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "portioner");
        Measure measure = createMeasure(body.toString());

        JSONObject updateBody = new JSONObject()
                .put("id", measure.getId())
                .put("name", "stycken");

        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/measure")
                .then()
                .statusCode(200)
                .log().all()
                .assertThat()
                .body(containsString("stycken"));
    }

    @Test
    public void deleteMeasure() throws JSONException {
        JSONObject body = new JSONObject()
                .put("name", "portioner");
        Measure measure = createMeasure(body.toString());

        RestAssured.given().contentType("application/json")
                .delete("/measure/" + measure.getId())
                .then()
                .log().all()
                .statusCode(200);
    }

    private Measure createMeasure(String body) {

        System.out.println("..............." + body.toString());

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("/measure")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(Measure.class);
    }
}