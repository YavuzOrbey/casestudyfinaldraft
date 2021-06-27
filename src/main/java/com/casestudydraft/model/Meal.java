package com.casestudydraft.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;
@Entity
@Table(name="meal")
public class Meal extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date")
    private Date date;

    @ManyToOne
    private MealType mealType;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "meal_recipe",
            joinColumns = @JoinColumn(name="meal_id"),
            inverseJoinColumns = @JoinColumn(name="recipe_id")
    )
    private Set<Recipe> recipe;

    public Meal() {
        super();
    }
}
