package se.wenzin.foodiecalc.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.dto.FoodCategoryDto;
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.repo.FoodCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodCategoryService {

    @Autowired
    FoodCategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public FoodCategory createFoodCategory(FoodCategory foodCategory) {

        return repository.save(foodCategory);

    }

    public Optional<FoodCategoryDto> findById(UUID id) {
        Optional<FoodCategory> foodCategory = repository.findById(id);
        if(foodCategory.isEmpty()){
            return Optional.empty();
        }
        FoodCategoryDto foodCategoryDto = convertToDto(foodCategory.get());
        return Optional.of(foodCategoryDto);
    }

    public List<FoodCategoryDto> findAll() {
        List<FoodCategory> foodCategories = repository.findAll();

        return foodCategories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

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
