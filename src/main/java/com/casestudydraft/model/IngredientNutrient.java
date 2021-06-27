package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name="ingredient_nutrient")
public class IngredientNutrient extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_id")
    @JsonBackReference("ingredientInIngredientNutrient")
    private Ingredient ingredient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nutrient_id")
    @JsonBackReference("nutrientInIngredientNutrient")
    private Nutrient nutrient;

    @Column(name = "amount")
    private int amount;

    public IngredientNutrient() {
    }

    public IngredientNutrient(Nutrient nutrient){
        this.nutrient = nutrient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IngredientNutrient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", nutrient=" + nutrient +
                ", amount=" + amount +
                '}';
    }
}
