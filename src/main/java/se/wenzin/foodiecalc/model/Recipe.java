package se.wenzin.foodiecalc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "RECIPE")
@Getter
@Setter
@NoArgsConstructor
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PORTIONSORAMOUNT")
    private String portionsOrAmount;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "FOODCATEGORYID")
    private UUID foodCategoryId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;

}
