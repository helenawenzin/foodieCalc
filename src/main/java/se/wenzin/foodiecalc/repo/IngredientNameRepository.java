package se.wenzin.foodiecalc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.wenzin.foodiecalc.model.IngredientName;

import java.util.UUID;

@Repository
public interface IngredientNameRepository extends JpaRepository <IngredientName, UUID> {
}
