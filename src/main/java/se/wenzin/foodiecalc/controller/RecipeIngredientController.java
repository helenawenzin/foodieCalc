package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.service.RecipeIngredientService;
import se.wenzin.foodiecalc.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientService service;

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredients")
    public ResponseEntity<List<RecipeIngredient>> getAllRecipeIngredients() {
        return ResponseEntity.ok(service.getAllRecipeIngredients());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredient/{id}")
    public ResponseEntity<Optional<RecipeIngredient>> getRecipeIngredientById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getRecipeIngredientById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{recipeId}/recipeingredient")
    public ResponseEntity<RecipeIngredient> createRecipeIngredient(@PathVariable(value = "recipeId") UUID recipeId,
                                                                   @RequestBody RecipeIngredient recipeIngredient) throws Exception {
        return ResponseEntity.ok(service.createRecipeIngredient(recipeId, recipeIngredient));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeId}/recipeingredient")
    public ResponseEntity<RecipeIngredient> updateRecipeIngredient(@PathVariable(value = "recipeId") UUID recipeId,
                                                                   @RequestBody RecipeIngredient recipeIngredient) throws Exception {
        return ResponseEntity.ok(service.updateRecipeIngredient(recipeId, recipeIngredient));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipeingredient/{id}")
    public void removeRecipeIngredient(@PathVariable("id") UUID id) {
        service.removeRecipeIngredient(id);
    }

}
