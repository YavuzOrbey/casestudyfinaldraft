package com.casestudydraft.model;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="mealtype")
public class MealType extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    public MealType() {
        super();
    }
}
