package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;
import se.wenzin.foodiecalc.service.RecipeIngredientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientService service;

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredient/{id}")
    public ResponseEntity<RecipeIngredientDto> getRecipeIngredientById(@PathVariable("id") UUID id) {
        Optional<RecipeIngredientDto> optionalDto = service.getRecipeIngredientById(id);
        if (optionalDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDto.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recipeingredients")
    public ResponseEntity<List<RecipeIngredientDto>> getAllRecipeIngredients() {
        return ResponseEntity.ok(service.getAllRecipeIngredients());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipeingredient")
    public ResponseEntity<RecipeIngredientDto> createRecipeIngredient(@RequestBody RecipeIngredientDto recipeIngredientDto) throws Exception {
        return ResponseEntity.ok(service.createRecipeIngredient(recipeIngredientDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/recipeingredient")
    public ResponseEntity<RecipeIngredientDto> updateRecipeIngredient(@RequestBody RecipeIngredientDto recipeIngredientDto) throws Exception {
        RecipeIngredientDto savedRecipeIngredientDto = service.updateRecipeIngredient(recipeIngredientDto);
        return ResponseEntity.ok(service.updateRecipeIngredient(recipeIngredientDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipeingredient/{id}")
    public void removeRecipeIngredient(@PathVariable("id") UUID id) {
        service.removeRecipeIngredient(id);
    }

}
