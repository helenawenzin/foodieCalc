package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.repo.IngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients")
    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ingredient/{id}")
    public Optional<Ingredient> getIngredientById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ingredient")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ingredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ingredient/{id}")
    public void removeIngredient(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
