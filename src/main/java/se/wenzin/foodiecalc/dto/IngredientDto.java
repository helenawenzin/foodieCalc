package se.wenzin.foodiecalc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class IngredientDto {
    private UUID id;
    private String name;

    private Long price_purchase;
    private Long weight_purchase;
    private Long amount_purchase;
    private Long weight_dl;
    private Long weight_msk;
    private Long weight_tsk;
    private Long weight_krm;
}
