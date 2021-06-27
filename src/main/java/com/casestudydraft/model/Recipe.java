package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="recipe")
public class Recipe extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    @NotEmpty(message="Name is required")
    private String name;

    @Column(name="cuisine")
    private String cuisine;

    @Column
    @ManyToMany(mappedBy = "recipe")
    @JsonIgnore
    Set<Meal> meal;

    @OneToMany(mappedBy = "recipe", cascade = {
            CascadeType.ALL
    }) //use the mapped by to avoid making a join table with one to many
    @Size(min=1, message = "Recipe must have at least one step")
    List<Step> recipeSteps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = {
            CascadeType.ALL
    })
    @Size(min=1, message = "Recipe must have at least one ingredient")
    //@JsonManagedReference
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany(mappedBy = "recipes")
    @JsonBackReference("usersInRecipe")
    Set<User> users;

    @NotNull
    boolean published = false;

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Recipe() {
        super();
    }

    public Recipe(String name) {
        super();
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Meal> getMeal() {
        return meal;
    }

    public void setMeal(Set<Meal> meal) {
        this.meal = meal;
    }

    public List<Step> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<Step> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                " }";
    }
}
