package se.wenzin.foodiecalc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.RecipeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<RecipeDto> getRecipeById(UUID id) {
        Optional<Recipe> recipe = repository.findById(id);
        if (recipe.isEmpty()) {
            return Optional.empty();
        }

        BigDecimal totalCost = calculateTotalCostForRecipe(recipe.get().getRecipeIngredients());

        RecipeDto recipeDto = convertToDto(recipe.get());
        recipeDto.setTotalCost(totalCost);
        return Optional.of(recipeDto);
    }

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = repository.findAll();

        return recipes.stream()
                .map(this::convertToDto)
                .map(recipeDto -> {
                    recipeDto.setTotalCost(calculateTotalCostForRecipe(convertToEntity(recipeDto).getRecipeIngredients()));
                    return recipeDto;
                })
                .collect(Collectors.toList());
    }

    public RecipeDto createRecipe(RecipeDto recipeDto) {
        Recipe savedRecipe = repository.save(convertToEntity(recipeDto));
        return convertToDto(savedRecipe);
    }

    public RecipeDto updateRecipe(RecipeDto recipeDto) {

        Recipe storedRecipe = repository.findById(recipeDto.getId()).orElseThrow(() -> new RuntimeException("Recipe not found"));
        // TODO: Fix this ugly hack
        storedRecipe.setInstructions(recipeDto.getInstructions() == null ? storedRecipe.getInstructions() : recipeDto.getInstructions());
        storedRecipe.setTitle(recipeDto.getTitle() == null ? storedRecipe.getTitle() : recipeDto.getTitle());
        storedRecipe.setPortionsOrAmount(recipeDto.getPortionsOrAmount() == null ? storedRecipe.getPortionsOrAmount() : recipeDto.getPortionsOrAmount());
        storedRecipe.setFoodCategoryId(recipeDto.getFoodCategoryId() == null ? storedRecipe.getFoodCategoryId() : recipeDto.getFoodCategoryId());

        Recipe recipe = repository.save(storedRecipe);

        BigDecimal totalCost = calculateTotalCostForRecipe(recipe.getRecipeIngredients());
        RecipeDto recipeDto2 = convertToDto(recipe);
        recipeDto2.setTotalCost(totalCost);

        return recipeDto2;
    }

    public void removeRecipe(UUID id) {
        repository.deleteById(id);
    }

    private Recipe convertToEntity(RecipeDto dto) {
        Recipe recipe = modelMapper.map(dto, Recipe.class);
        return recipe;
    }

    private RecipeDto convertToDto(Recipe recipe) {
        RecipeDto dto = modelMapper.map(recipe, RecipeDto.class);
        return dto;
    }

    private BigDecimal calculateTotalCostForRecipe(Set<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(RecipeIngredient::getCost)
                .reduce(BigDecimal::add)
                .get();
    }
}
