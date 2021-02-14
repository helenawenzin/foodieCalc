package se.wenzin.foodiecalc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.dto.IngredientDto;
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.repo.IngredientRepository;
import se.wenzin.foodiecalc.service.IngredientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @Autowired
    private IngredientService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/ingredient/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable("id") UUID id) {
        Optional<IngredientDto> optionalDto = service.findById(id);
        if(optionalDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDto.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ingredients")
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        List<IngredientDto> optionalDtos = service.findAll();
        return ResponseEntity.ok(optionalDtos);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ingredient")
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient savedIngredient = service.createIngredient(convertToEntity(ingredientDto));
        return ResponseEntity.ok(convertToDto(savedIngredient));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ingredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/ingredient/{id}")
    public void removeIngredient(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }


    private Ingredient convertToEntity(IngredientDto ingredientDto) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        return ingredient;
    }

    private IngredientDto convertToDto(Ingredient ingredient) {
        IngredientDto ingredientDto = modelMapper.map(ingredient, IngredientDto.class);
        return ingredientDto;
    }

}
