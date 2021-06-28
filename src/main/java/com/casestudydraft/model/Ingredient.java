package com.casestudydraft.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

//Ingredient entity...because my entites use Jackson for JSON mapping I had to add those annotations
@Entity
@Table(name="ingredient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="serving_size")
    @NotNull(message = "Required")
    @Min(1)
    private Integer servingSize;

    @Column(name="calories")
    @NotNull(message = "Required")
    @Min(0)
    private Integer calories;

    @Column(name="name")
    @NotEmpty(message="Required")
    private String name;

    @ManyToOne
    @JoinColumn(name="fk_servingSizeMeasurement")
    @JsonIgnore
    private Measurement measurement;


    @ManyToOne
    @JoinColumn(name="fk_categoryId")
    private Category category;

    @OneToMany(mappedBy = "ingredient", cascade = {
            CascadeType.ALL
    })
    @JsonBackReference("ingredientNutrients")
    private List<IngredientNutrient> ingredientNutrients = new ArrayList<>();

    //if the ingredient gets deleted make sure to cascade that to the recipe ingredients
    @OneToMany(mappedBy = "ingredient", cascade = {
            CascadeType.ALL
    })
    @JsonBackReference("recipeIngredients")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "ingredient", cascade = {
            CascadeType.ALL
    })
    @JsonBackReference("pantryIngredients")
    private List<PantryIngredient> pantryIngredients = new ArrayList<>();


    public Ingredient() {
        super();
    }

    public Ingredient(String name, Integer servingSize, Integer calories) {
        super();
        this.name = name;
        this.servingSize = servingSize;
        this.calories = calories;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngredientNutrient> getIngredientNutrients() {
        return ingredientNutrients;
    }

    public void setIngredientNutrients(List<IngredientNutrient> ingredientNutrients) {
        this.ingredientNutrients = ingredientNutrients;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<PantryIngredient> getPantryIngredients() {
        return pantryIngredients;
    }

    public void setPantryIngredients(List<PantryIngredient> pantryIngredients) {
        this.pantryIngredients = pantryIngredients;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", servingSize=" + servingSize +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                '}';
    }
}