package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.repo.RecipeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/recipes")
    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipe/{id}")
    public Optional<Recipe> getRecipe(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return repository.save(recipe);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/recipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        return repository.save(recipe);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipe/{id}")
    public void removeRecipe(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
