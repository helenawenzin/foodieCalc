package se.wenzin.foodiecalc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.dto.RecipeDto;
import se.wenzin.foodiecalc.dto.RecipeIngredientDto;
import se.wenzin.foodiecalc.model.Recipe;
import se.wenzin.foodiecalc.model.RecipeIngredient;
import se.wenzin.foodiecalc.repo.RecipeIngredientRepository;

import java.math.BigDecimal;
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
    private IngredientService ingredientService;

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

        RecipeIngredientDto recipeIngredientDtoWithCost = calculateCostForRecipeIngredient(recipeIngredientDto);

        RecipeIngredient savedRecipeIngredient = repository.save(convertToEntity(recipeIngredientDtoWithCost));
        return convertToDto(savedRecipeIngredient);
    }

    public RecipeIngredientDto updateRecipeIngredient(RecipeIngredientDto recipeIngredientDto) throws Exception {
        // TODO: Fix this ugly hack

        RecipeIngredient storedRecipeIngredient = repository.findById(recipeIngredientDto.getId()).orElseThrow(() -> new RuntimeException("RecipeIngredient not found"));
        storedRecipeIngredient.setIngredientId(recipeIngredientDto.getIngredientId() == null ? storedRecipeIngredient.getIngredientId() : recipeIngredientDto.getIngredientId());
        storedRecipeIngredient.setMeasure(recipeIngredientDto.getMeasure() == null ? storedRecipeIngredient.getMeasure() : recipeIngredientDto.getMeasure());
        storedRecipeIngredient.setQuantity(recipeIngredientDto.getQuantity() == null ? storedRecipeIngredient.getQuantity() : recipeIngredientDto.getQuantity());

        RecipeIngredientDto recipeIngredientDtoWithCost = calculateCostForRecipeIngredient(convertToDto(storedRecipeIngredient));

        RecipeIngredient recipeIngredient = repository.save(convertToEntity(recipeIngredientDtoWithCost));
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

    private RecipeIngredientDto calculateCostForRecipeIngredient(RecipeIngredientDto recipeIngredientDto) throws Exception {

        Optional<IngredientDto> ingredientDto = ingredientService.findById(recipeIngredientDto.getIngredientId());
        if (ingredientDto.isEmpty()) {
            throw new Exception("Ingredient not found");
        }

        BigDecimal purchasePrice = ingredientDto.get().getPurchasePrice();
        Long purchaseQuantity = ingredientDto.get().getPurchaseQuantity();
        Long quantity = recipeIngredientDto.getQuantity();

        BigDecimal finalCost = null;

        if (!(purchaseQuantity == null)) {
            finalCost = purchasePrice
                    .divide(new BigDecimal(purchaseQuantity))
                    .multiply(new BigDecimal(quantity));
        } else {

            Long weightInDl = ingredientDto.get().getOneDeciliterWeight();
            Long weightForThisIngredient = switch (recipeIngredientDto.getMeasure()) {
                case "dl" -> weightInDl;
                case "msk" -> weightInDl / 6;
                case "tsk" -> weightInDl / 20;
                case "krm" -> weightInDl / 60;
                case "gr" -> 1L;
                default -> throw new IllegalStateException("Unexpected value: " + recipeIngredientDto.getMeasure());
            };

            finalCost = purchasePrice
                    .divide(new BigDecimal(ingredientDto.get().getPurchaseWeight()))
                    .multiply(new BigDecimal(quantity * weightForThisIngredient));
        }

        recipeIngredientDto.setCost(finalCost);
        return recipeIngredientDto;
    }
}
