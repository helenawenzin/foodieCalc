package se.wenzin.foodiecalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.wenzin.foodiecalc.dto.FoodCategoryDto;
import se.wenzin.foodiecalc.service.FoodCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService service;

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategory/{id}")
    public ResponseEntity<FoodCategoryDto> getFoodCategoryById(@PathVariable("id") UUID id) {
        Optional<FoodCategoryDto> optionalDto = service.findById(id);
        if (optionalDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDto.get());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/foodcategories")
    public ResponseEntity<List<FoodCategoryDto>> getAllFoodCategories() {
        List<FoodCategoryDto> optionalDtos = service.findAll();
        return ResponseEntity.ok(optionalDtos);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/foodcategory")
    public ResponseEntity<FoodCategoryDto> createFoodCategory(@RequestBody FoodCategoryDto foodCategoryDto) {
        return ResponseEntity.ok(service.createFoodCategory(foodCategoryDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/foodcategory")
    public ResponseEntity<FoodCategoryDto> updateFoodCategory(@RequestBody FoodCategoryDto foodCategoryDto) {
        FoodCategoryDto savedFoodCategoryDto = service.updateFoodCategory(foodCategoryDto);
        return ResponseEntity.ok(savedFoodCategoryDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/foodcategory/{id}")
    public void removeFoodCategory(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }
}
