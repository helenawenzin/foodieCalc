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
import java.math.BigDecimal;
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

    @Column(name = "PURCHASEPRICE")
    private BigDecimal purchasePrice;

    @Column(name = "PURCHASEWEIGHTORQUANTITY")
    private Long purchaseWeightOrQuantity;

    @Column(name = "ONEDECILITERWEIGHT")
    private Long oneDeciliterWeight;

}
