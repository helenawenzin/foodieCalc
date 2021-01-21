package se.wenzin.foodiecalc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "FOODCATEGORY")
public class FoodCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    public FoodCategory() {}

    public FoodCategory(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getId() {
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
