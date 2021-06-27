package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="nutrient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrient extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "nutrient", cascade = {
            CascadeType.ALL
    })
    @JsonIgnore
    private List<IngredientNutrient> ingredientNutrients = new ArrayList<>();;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Measurement measurement;


    public Nutrient() {
    }


    public Nutrient(String name, Measurement measurement) {
        super();
        this.name = name;
        this.measurement = measurement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    @JsonIgnore
    public List<IngredientNutrient> getIngredientNutrients() {
        return ingredientNutrients;
    }

    public void setIngredientNutrients(List<IngredientNutrient> ingredientNutrients) {
        this.ingredientNutrients = ingredientNutrients;
    }


    @Override
    public String toString() {
        return "Nutrient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measurement=" + measurement +
                '}';
    }
}
