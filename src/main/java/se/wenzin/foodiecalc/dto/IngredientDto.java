package se.wenzin.foodiecalc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class IngredientDto {
    private UUID id;
    private String name;
}
