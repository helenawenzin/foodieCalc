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
        Recipe recipe = recipeService.getRecipe((recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found."));
        recipeIngredient.setRecipe(recipe);

        return repository.save(recipeIngredient);
    }

    public RecipeIngredient updateRecipeIngredient(UUID recipeId, RecipeIngredient recipeIngredient) throws Exception {
        // TODO: Fix this ugly hack
        RecipeIngredient storedRecipeIngredient = repository.findById(recipeIngredient.getId()).orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
        storedRecipeIngredient.setIngredientId(recipeIngredient.getIngredientId() == null ? storedRecipeIngredient.getIngredientId() : recipeIngredient.getIngredientId());
        storedRecipeIngredient.setMeasureId(recipeIngredient.getMeasureId() == null ? storedRecipeIngredient.getMeasureId() : recipeIngredient.getMeasureId());
        storedRecipeIngredient.setQuantity(recipeIngredient.getQuantity() == null ? storedRecipeIngredient.getQuantity() : recipeIngredient.getQuantity());

        recipeService.getRecipe((recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found."));
        storedRecipeIngredient.setRecipe(recipeIngredient.getRecipe() == null ? storedRecipeIngredient.getRecipe() : recipeIngredient.getRecipe());

        return repository.save(storedRecipeIngredient);
    }

    public void removeRecipeIngredient(UUID id) {
        repository.deleteById(id);
    }

}
