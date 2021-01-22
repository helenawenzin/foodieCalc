package se.wenzin.foodiecalc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.wenzin.foodiecalc.model.MeasureUnit;

import java.util.UUID;

public interface UnitRepository extends JpaRepository<MeasureUnit, UUID> {
}
