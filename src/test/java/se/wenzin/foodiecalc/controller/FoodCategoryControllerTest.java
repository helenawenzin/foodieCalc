package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
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

    @Test
    public void getFoodCategory() {

        String body = "{\"name\": \"dinner\"}";
        FoodCategory foodCategory = createFoodCategory(body);

        RestAssured.given().contentType("application/json")
                .get("http://localhost:" + serverProperties.getPort() + "/foodcategory/" + foodCategory.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void getFoodcategories() {
        String body = "{\"name\": \"dessert\"}";
        createFoodCategory(body);
        String body2 = "{\"name\": \"lunch\"}";
        createFoodCategory(body2);


        RestAssured.given().contentType("application/json")
                .get("http://localhost:" + serverProperties.getPort() + "/foodcategories")
                .then()
                .log().all()
                .statusCode(200)
                .body("[0]", hasKey("id"))
                .assertThat().body(notNullValue());


    }

    private FoodCategory createFoodCategory(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("http://localhost:" + serverProperties.getPort() + "/foodcategory")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(FoodCategory.class);
    }

}