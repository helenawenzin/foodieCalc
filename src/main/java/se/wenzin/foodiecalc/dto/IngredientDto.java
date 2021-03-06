package se.wenzin.foodiecalc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {
    private UUID id;
    private String name;

    private BigDecimal purchasePrice;
    private Long purchaseWeightOrQuantity;

    //all vikt är i gram
    private Long oneDeciliterWeight;

}

