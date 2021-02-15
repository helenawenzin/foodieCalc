package se.wenzin.foodiecalc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "INGREDIENT")
@Data
@NoArgsConstructor
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE_PURCHASE")
    private Long price_purchase;

    @Column(name = "WEIGHT_PURCHASE")
    private Long weight_purchase;

    @Column(name = "AMOUNT_PURCHASE")
    private Long amount_purchase;

    @Column(name = "WEIGHT_DL")
    private Long weight_dl;

    @Column(name = "WEIGHT_MSK")
    private Long weight_msk;

    @Column(name = "WEIGHT_TSK")
    private Long weight_tsk;

    @Column(name = "WEIGHT_KRM")
    private Long weight_krm;
}
