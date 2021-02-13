package se.wenzin.foodiecalc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.wenzin.foodiecalc.model.Ingredient;
import se.wenzin.foodiecalc.repo.IngredientRepository;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository repository;

    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }
}
