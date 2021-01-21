package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import se.wenzin.foodiecalc.model.FoodCategory;


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