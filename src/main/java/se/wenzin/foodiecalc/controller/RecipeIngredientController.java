package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.RecipeIngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredients")
    public List<RecipeIngredient> getAllRecipeIngredients() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredient/{id}")
    public Optional<RecipeIngredient> getRecipeIngredientById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipeingredient")
    public RecipeIngredient createRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/recipeingredient")
    public RecipeIngredient updateRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipeingredient/{id}")
    public void removeRecipeIngredient(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
