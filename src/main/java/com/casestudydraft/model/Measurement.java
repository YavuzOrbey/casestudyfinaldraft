package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="measurement")
public class Measurement extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(mappedBy="measurement", cascade = CascadeType.ALL)
    @JsonBackReference("nutrients")
    private List<Nutrient> nutrients;

    @OneToMany(mappedBy="measurement", cascade = CascadeType.ALL)
    @JsonBackReference("recipeIngredients")
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy="measurement", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    public Measurement() {
    }

    public Measurement(String name) {
        super();
        this.name = name;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    //@JsonIgnore
    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System.out.println("Got to here");
        Measurement that = (Measurement) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}