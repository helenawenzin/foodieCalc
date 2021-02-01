package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(method = RequestMethod.GET, value = "/recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipe/{id}")
    public ResponseEntity<Optional<Recipe>> getRecipe(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/recipe")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.updateRecipe(recipe));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipe/{id}")
    public void removeRecipe(@PathVariable("id") UUID id) {
        recipeService.removeRecipe(id);
    }

}
