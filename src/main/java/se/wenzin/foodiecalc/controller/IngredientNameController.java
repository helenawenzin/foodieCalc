package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.IngredientName;
import se.wenzin.foodiecalc.repo.IngredientNameRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IngredientNameController {

    @Autowired
    private IngredientNameRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/ingredientnames")
    public List<IngredientName> getAllIngredientNames() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ingredientname/{id}")
    public Optional<IngredientName> getIngredientNameById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ingredientname")
    public IngredientName createIngredientName(@RequestBody IngredientName ingredientName) {
        return repository.save(ingredientName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ingredientname")
    public IngredientName updateIngredientName(@RequestBody IngredientName ingredientName) {
        return repository.save(ingredientName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ingredientname/{id}")
    public void removeIngredientName(@PathVariable("id") UUID id) {
        repository.deleteById(id);    }

}
