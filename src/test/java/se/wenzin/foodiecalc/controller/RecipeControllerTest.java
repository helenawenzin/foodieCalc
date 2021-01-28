package se.wenzin.foodiecalc.controller;

import io.restassured.RestAssured;
import org.json.JSONArray;
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
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.model.Recipe;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static se.wenzin.foodiecalc.controller.TestControllerUtil.createRecipe;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RecipeControllerTest {

    @Autowired
    private ServerProperties serverProperties;

    @BeforeEach
    public void before() {
        RestAssured.baseURI = "http://localhost:" + serverProperties.getPort();
    }

    @Test
    public void getRecipeById() throws JSONException {
        JSONObject bodyForCreatingIngredient1 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 5);

        JSONObject bodyForCreatingIngredient2 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 10);

        Ingredient ingredient1 = TestControllerUtil.createIngredient(bodyForCreatingIngredient1);
        Ingredient ingredient2 = TestControllerUtil.createIngredient(bodyForCreatingIngredient2);

        JSONObject ingredientWithId1 = new JSONObject().put("id", ingredient1.getId())
                .put("ingredientNameId", ingredient1.getIngredientNameId())
                .put("measureId", ingredient1.getMeasureId())
                .put("quantity", ingredient1.getQuantity());
        JSONObject ingredientWithId2 = new JSONObject().put("id", ingredient2.getId())
                .put("ingredientNameId", ingredient2.getIngredientNameId())
                .put("measureId", ingredient2.getMeasureId())
                .put("quantity", ingredient2.getQuantity());

        JSONArray ingredientsForRecipeBody = new JSONArray()
                .put(ingredientWithId1)
                .put(ingredientWithId2);

        JSONObject recipeBody = new JSONObject()
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody)
                .put("instructions", "1. Blanda alla ingredienser. 2. In i ugnen ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .get("/recipe/" + recipe.getId())
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("1. Blanda alla ingredienser. 2. In i ugnen "))
                .and().body(containsString(recipe.getId().toString()));
    }

    @Test
    public void getRecipes() throws JSONException {

        JSONObject bodyForCreatingIngredient1 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 5);

        JSONObject bodyForCreatingIngredient2 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 10);

        Ingredient ingredient1 = TestControllerUtil.createIngredient(bodyForCreatingIngredient1);
        Ingredient ingredient2 = TestControllerUtil.createIngredient(bodyForCreatingIngredient2);

        JSONObject ingredientWithId1 = new JSONObject().put("id", ingredient1.getId())
                .put("ingredientNameId", ingredient1.getIngredientNameId())
                .put("measureId", ingredient1.getMeasureId())
                .put("quantity", ingredient1.getQuantity());
        JSONObject ingredientWithId2 = new JSONObject().put("id", ingredient2.getId())
                .put("ingredientNameId", ingredient2.getIngredientNameId())
                .put("measureId", ingredient2.getMeasureId())
                .put("quantity", ingredient2.getQuantity());

        JSONArray ingredientsForRecipeBody = new JSONArray()
                .put(ingredientWithId1)
                .put(ingredientWithId2);

        JSONObject recipeBody = new JSONObject()
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody)
                .put("instructions", "1. Blanda alla ingredienser. 2. In i ugnen ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe1 = createRecipe(recipeBody);

        JSONObject ingredientWithId1_1 = new JSONObject().put("id", ingredient1.getId())
                .put("ingredientNameId", ingredient1.getIngredientNameId())
                .put("measureId", ingredient1.getMeasureId())
                .put("quantity", ingredient1.getQuantity());
        ;
        JSONObject ingredientWithId2_2 = new JSONObject().put("id", ingredient2.getId())
                .put("ingredientNameId", ingredient2.getIngredientNameId())
                .put("measureId", ingredient2.getMeasureId())
                .put("quantity", ingredient2.getQuantity());
        ;


        JSONArray ingredientsForRecipeBody2 = new JSONArray()
                .put(ingredientWithId1_1)
                .put(ingredientWithId2_2);

        JSONObject recipeBody2 = new JSONObject()
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody2)
                .put("instructions", "Bara blanda allt! Ät fort! ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe2 = createRecipe(recipeBody2);

        RestAssured.given().contentType("application/json")
                .get("/recipes")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("Bara blanda allt! Ät fort! "))
                .and().body(containsString("1. Blanda alla ingredienser. 2. In i ugnen "))
                .and().body(containsString(recipe1.getId().toString()))
                .and().body(containsString(recipe2.getId().toString()));
    }

    @Test
    public void updateRecipe() throws JSONException {

        JSONObject bodyForCreatingIngredient1 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 5);

        JSONObject bodyForCreatingIngredient2 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 10);

        Ingredient ingredient1 = TestControllerUtil.createIngredient(bodyForCreatingIngredient1);
        Ingredient ingredient2 = TestControllerUtil.createIngredient(bodyForCreatingIngredient2);

        JSONObject ingredientWithId1 = new JSONObject().put("id", ingredient1.getId())
                .put("ingredientNameId", ingredient1.getIngredientNameId())
                .put("measureId", ingredient1.getMeasureId())
                .put("quantity", ingredient1.getQuantity());
        JSONObject ingredientWithId2 = new JSONObject().put("id", ingredient2.getId())
                .put("ingredientNameId", ingredient2.getIngredientNameId())
                .put("measureId", ingredient2.getMeasureId())
                .put("quantity", ingredient2.getQuantity());

        JSONArray ingredientsForRecipeBody = new JSONArray()
                .put(ingredientWithId1)
                .put(ingredientWithId2);

        JSONObject recipeBody = new JSONObject()
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody)
                .put("instructions", "1. Blanda alla ingredienser. 2. In i ugnen ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        JSONObject updateBody = new JSONObject()
                .put("id", recipe.getId())
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody)
                .put("foodCategoryId", recipe.getFoodCategoryId())
                .put("instructions", "En massa nya instruktioner");


        RestAssured.given().contentType("application/json")
                .body(updateBody.toString())
                .put("/recipe")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body(containsString("En massa nya instruktioner"));

    }

    @Test
    public void deleteRecipe() throws JSONException {
        JSONObject bodyForCreatingIngredient1 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 5);

        JSONObject bodyForCreatingIngredient2 = new JSONObject()
                .put("ingredientNameId", UUID.randomUUID())
                .put("measureId", UUID.randomUUID())
                .put("quantity", 10);

        Ingredient ingredient1 = TestControllerUtil.createIngredient(bodyForCreatingIngredient1);
        Ingredient ingredient2 = TestControllerUtil.createIngredient(bodyForCreatingIngredient2);

        JSONObject ingredientWithId1 = new JSONObject().put("id", ingredient1.getId())
                .put("ingredientNameId", ingredient1.getIngredientNameId())
                .put("measureId", ingredient1.getMeasureId())
                .put("quantity", ingredient1.getQuantity());
        JSONObject ingredientWithId2 = new JSONObject().put("id", ingredient2.getId())
                .put("ingredientNameId", ingredient2.getIngredientNameId())
                .put("measureId", ingredient2.getMeasureId())
                .put("quantity", ingredient2.getQuantity());

        JSONArray ingredientsForRecipeBody = new JSONArray()
                .put(ingredientWithId1)
                .put(ingredientWithId2);

        JSONObject recipeBody = new JSONObject()
                .put("portionsOrAmountId", UUID.randomUUID())
                .put("ingredients", ingredientsForRecipeBody)
                .put("instructions", "1. Blanda alla ingredienser. 2. In i ugnen ")
                .put("foodCategoryId", UUID.randomUUID());

        Recipe recipe = createRecipe(recipeBody);

        RestAssured.given().contentType("application/json")
                .delete("/recipe/" + recipe.getId())
                .then()
                .log().all()
                .statusCode(200);
    }
}