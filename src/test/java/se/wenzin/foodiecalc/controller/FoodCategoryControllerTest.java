package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.wenzin.foodiecalc.model.FoodCategory;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FoodCategoryControllerTest {

    @Test
    public void getFoodCategory() {

        String body = "{\"name\": \"dessert\"}";
        FoodCategory foodCategory = createFoodCategory(body);

        RestAssured.given().contentType("application/json")
                .get("http://localhost:8080/foodcategory/" + foodCategory.getId())
                .then()
                .statusCode(200);
    }

    private FoodCategory createFoodCategory(String body) {

        return RestAssured.given().contentType("application/json")
                .body(body)
                .post("http://localhost:8080/foodcategory")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .body()
                .as(FoodCategory.class);
    }

}