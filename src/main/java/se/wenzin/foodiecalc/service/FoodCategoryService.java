package se.wenzin.foodiecalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.model.FoodCategory;
import se.wenzin.foodiecalc.repo.FoodCategoryRepository;

@Service
public class FoodCategoryService {

    @Autowired
    FoodCategoryRepository repository;

    public FoodCategory createFoodCategory(FoodCategory foodCategory) {

        return repository.save(foodCategory);

    }
}
