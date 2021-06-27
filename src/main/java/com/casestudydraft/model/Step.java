package com.casestudydraft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="step")
public class Step extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="text")
    @NotEmpty(message="Required")
    private String text;

    @Column(name="stepOrder")
    @NotNull(message = "Required")
    @Min(1)
    private int stepOrder;

    @ManyToOne
    @JsonIgnore
    private Recipe recipe;


    public Step() {
    }

    public Step(String text) {
        this.text = text;
    }

    public Step(String text, int stepOrder) {
        super();
        this.text = text;
        this.stepOrder = stepOrder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", text=" + text +
                ", stepOrder=" + stepOrder +
                ", recipe=" + recipe +
                '}';
    }
}