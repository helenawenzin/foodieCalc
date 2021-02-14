package se.wenzin.foodiecalc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.repo.IngredientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Optional<IngredientDto> findById(UUID id) {
        Optional<Ingredient> ingredient = repository.findById(id);
        if (ingredient.isEmpty()) {
            return Optional.empty();
        }
        IngredientDto ingredientDto = convertToDto(ingredient.get());
        return Optional.of(ingredientDto);
    }

    public List<IngredientDto> findAll() {
        List<Ingredient> ingredients = repository.findAll();

        return ingredients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Ingredient convertToEntity(IngredientDto dto) {
        Ingredient ingredient = modelMapper.map(dto, Ingredient.class);
        return ingredient;
    }

    private IngredientDto convertToDto(Ingredient ingredient) {
        IngredientDto dto = modelMapper.map(ingredient, IngredientDto.class);
        return dto;
    }
}
