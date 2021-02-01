package se.wenzin.foodiecalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.RecipeIngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeIngredientService {

    @Autowired
    private RecipeIngredientRepository repository;

    @Autowired
    private RecipeService recipeService;

    public List<RecipeIngredient> getAllRecipeIngredients() {
        return repository.findAll();
    }

    public Optional<RecipeIngredient> getRecipeIngredientById(UUID id) {
        return repository.findById(id);
    }

    public RecipeIngredient createRecipeIngredient(UUID recipeId, RecipeIngredient recipeIngredient) throws Exception {
        Optional<Recipe> recipe = recipeService.getRecipe(recipeId);
        if (recipe.isPresent()) {
            recipeIngredient.setRecipe(recipe.get());
        } else {
            throw new Exception("Recipe with id:" + recipeId + " can not be found!");
        }
        return repository.save(recipeIngredient);
    }

    public RecipeIngredient updateRecipeIngredient(UUID recipeId, RecipeIngredient recipeIngredient) throws Exception {
        Optional<Recipe> recipe = recipeService.getRecipe(recipeId);
        if (recipe.isPresent()) {
            recipeIngredient.setRecipe(recipe.get());
        } else {
            throw new Exception("Recipe with id:" + recipeId + " can not be found!");
        }
        return repository.save(recipeIngredient);
    }

    public void removeRecipeIngredient(UUID id) {
        repository.deleteById(id);
    }

}
