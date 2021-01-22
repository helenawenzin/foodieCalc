package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.repo.FoodCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FoodCategoryController {

    @Autowired
    private FoodCategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategory/{id}")
    public Optional<FoodCategory> getFoodCategoryById(@PathVariable("id") UUID id) {
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategories")
    public List<FoodCategory> getAllFoodCategories() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/foodcategory")
    public FoodCategory createFoodCategory(@RequestBody FoodCategory foodCategory) {
        return repository.save(foodCategory);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/foodcategory")
    public FoodCategory updateFoodCategory(@RequestBody FoodCategory foodCategory) {
        return repository.save(foodCategory);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/foodcategory/{id}")
    public void removeFoodCategory(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

}
