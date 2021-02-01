package se.wenzin.foodiecalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.repo.RecipeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    public Optional<Recipe> getRecipe(UUID id) {
        return repository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return repository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        Recipe storedRecipe = repository.findById(recipe.getId()).orElseThrow(() -> new RuntimeException("Recipe not found"));
        // TODO: Fix this ugly hack
        storedRecipe.setInstructions(recipe.getInstructions() == null ? storedRecipe.getInstructions() : recipe.getInstructions());
        storedRecipe.setTitle(recipe.getTitle() == null ? storedRecipe.getTitle() : recipe.getTitle());
        storedRecipe.setPortionsOrAmount(recipe.getPortionsOrAmount() == null ? storedRecipe.getPortionsOrAmount() : recipe.getPortionsOrAmount());
        storedRecipe.setFoodCategoryId(recipe.getFoodCategoryId() == null ? storedRecipe.getFoodCategoryId() : recipe.getFoodCategoryId());
        return repository.save(storedRecipe);
    }

    public void removeRecipe(UUID id) {
        repository.deleteById(id);
    }
}
