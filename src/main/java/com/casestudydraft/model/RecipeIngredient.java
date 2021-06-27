package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class RecipeIngredient extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /* If the ingredient gets deleted the recipe ingredient should get deleted and if the recipe ingredient gets deleted
    then the recipe itself needs to get deleted. No point in having a recipe without that specific ingredient
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference("recipeInRecipeIngredient")
    private Recipe recipe;

    /* When the recipe ingredient changes however make no changes in the ingredient */
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @JsonBackReference("ingredientInRecipeIngredient")
    private Ingredient ingredient;

    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="fk_quantityMeasurement")
    @JsonIgnore
    private Measurement measurement;

    public RecipeIngredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", measurement=" + measurement +
                '}';
    }
}
