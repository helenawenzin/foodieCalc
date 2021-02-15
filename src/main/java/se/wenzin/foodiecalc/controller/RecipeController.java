package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService service;

    @RequestMapping(method = RequestMethod.GET, value = "/recipe/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("id") UUID id) {
        Optional<RecipeDto> optionalDto = service.getRecipeById(id);
        if (optionalDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDto.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> allRecipes = service.getAllRecipes();
        return ResponseEntity.ok(allRecipes);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe")
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok(service.createRecipe(recipeDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/recipe")
    public ResponseEntity<RecipeDto> updateRecipe(@RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok(service.updateRecipe(recipeDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipe/{id}")
    public void removeRecipe(@PathVariable("id") UUID id) {
        service.removeRecipe(id);
    }

}
