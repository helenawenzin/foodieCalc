package se.wenzin.foodiecalc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.RecipeIngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    @Autowired
    private RecipeIngredientRepository repository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ModelMapper modelMapper;


    public Optional<RecipeIngredientDto> getRecipeIngredientById(UUID id) {
        Optional<RecipeIngredient> recipeIngredient = repository.findById(id);
        if (recipeIngredient.isEmpty()) {
            return Optional.empty();
        }
        RecipeIngredientDto recipeIngredientDto = convertToDto(recipeIngredient.get());
        return Optional.of(recipeIngredientDto);
    }

    public List<RecipeIngredientDto> getAllRecipeIngredients() {
        List<RecipeIngredient> recipeIngredients = repository.findAll();
        return recipeIngredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RecipeIngredientDto createRecipeIngredient(RecipeIngredientDto recipeIngredientDto) throws Exception {
        //RecipeDto recipeDto = recipeService.getRecipeById((recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found."));
        RecipeIngredient savedRecipeIngredient = repository.save(convertToEntity(recipeIngredientDto));
        return convertToDto(savedRecipeIngredient);
    }

    public RecipeIngredientDto updateRecipeIngredient(RecipeIngredientDto recipeIngredientDto) throws Exception {
        // TODO: Fix this ugly hack
        //Is this necessary? :
        RecipeIngredient recipeIngredientConverted = convertToEntity(recipeIngredientDto);

        RecipeIngredient storedRecipeIngredient = repository.findById(recipeIngredientConverted.getId()).orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
        storedRecipeIngredient.setIngredientId(recipeIngredientConverted.getIngredientId() == null ? storedRecipeIngredient.getIngredientId() : recipeIngredientConverted.getIngredientId());
        storedRecipeIngredient.setMeasure(recipeIngredientConverted.getMeasure() == null ? storedRecipeIngredient.getMeasure() : recipeIngredientConverted.getMeasure());
        storedRecipeIngredient.setQuantity(recipeIngredientConverted.getQuantity() == null ? storedRecipeIngredient.getQuantity() : recipeIngredientConverted.getQuantity());

        //recipeService.getRecipeById((recipeId)).orElseThrow(() -> new RuntimeException("Recipe not found."));
        //storedRecipeIngredient.setRecipe(recipeIngredientDto.getRecipe() == null ? storedRecipeIngredient.getRecipe() : recipeIngredientDto.getRecipe());

        RecipeIngredient recipeIngredient = repository.save(storedRecipeIngredient);
        return convertToDto(recipeIngredient);
    }

    public void removeRecipeIngredient(UUID id) {
        repository.deleteById(id);
    }

    private RecipeIngredient convertToEntity(RecipeIngredientDto dto) {
        RecipeIngredient recipeIngredient = modelMapper.map(dto, RecipeIngredient.class);
        return recipeIngredient;
    }

    private RecipeIngredientDto convertToDto(RecipeIngredient recipeIngredient) {
        RecipeIngredientDto dto = modelMapper.map(recipeIngredient, RecipeIngredientDto.class);
        return dto;
    }

    private Recipe convertRecipeToEntity(RecipeDto dto) {
        Recipe recipe = modelMapper.map(dto, Recipe.class);
        return recipe;
    }

    private RecipeDto convertRecipeToDto(Recipe recipe) {
        RecipeDto dto = modelMapper.map(recipe, RecipeDto.class);
        return dto;
    }
}
