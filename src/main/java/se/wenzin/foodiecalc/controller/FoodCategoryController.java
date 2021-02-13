package se.wenzin.foodiecalc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.dto.FoodCategoryDto;
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.repo.FoodCategoryRepository;
import se.wenzin.foodiecalc.service.FoodCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class FoodCategoryController {

    @Autowired
    private FoodCategoryRepository repository;

    @Autowired
    private FoodCategoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategory/{id}")
    public ResponseEntity<FoodCategoryDto> getFoodCategoryById(@PathVariable("id") UUID id) {
        Optional<FoodCategoryDto> optionalDto = service.findById(id);
        if(optionalDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDto.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategories")
    public List<FoodCategory> getAllFoodCategories() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/foodcategory")
    public ResponseEntity<FoodCategoryDto> createFoodCategory(@RequestBody FoodCategoryDto foodCategoryDto) {
        FoodCategory savedFoodCategory = service.createFoodCategory(convertToEntity(foodCategoryDto));
        return ResponseEntity.ok(convertToDto(savedFoodCategory));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/foodcategory")
    public FoodCategory updateFoodCategory(@RequestBody FoodCategory foodCategory) {
        return repository.save(foodCategory);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/foodcategory/{id}")
    public void removeFoodCategory(@PathVariable("id") UUID id) {
        repository.deleteById(id);
    }

    private FoodCategory convertToEntity(FoodCategoryDto dto) {
        FoodCategory foodCategory = modelMapper.map(dto, FoodCategory.class);
        return foodCategory;
    }

    private FoodCategoryDto convertToDto(FoodCategory foodCategory) {
        FoodCategoryDto dto = modelMapper.map(foodCategory, FoodCategoryDto.class);
        return dto;
    }

}
