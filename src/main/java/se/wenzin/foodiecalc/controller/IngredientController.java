package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.IngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients")
    public List<RecipeIngredient> getAllIngredients() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ingredient/{id}")
    public Optional<RecipeIngredient> getIngredientById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ingredient")
    public RecipeIngredient createIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ingredient")
    public RecipeIngredient updateIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        return repository.save(recipeIngredient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ingredient/{id}")
    public void removeIngredient(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
