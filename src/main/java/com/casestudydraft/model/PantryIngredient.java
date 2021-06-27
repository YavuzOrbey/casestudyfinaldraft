package com.casestudydraft.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
@Entity
@Table(name="pantry_ingredient")
public class PantryIngredient extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pantry_id")
    private Pantry pantry;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredient_id")

    private Ingredient ingredient;

    @Min(0)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="fk_quantityMeasurement")
    private Measurement measurement;

    public PantryIngredient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
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
        return "PantryIngredient{" +
                "id=" + id +
                ", pantry=" + pantry +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", measurement=" + measurement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PantryIngredient that = (PantryIngredient) o;

        if (!pantry.equals(that.pantry)) return false;
        return ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        int result = pantry.hashCode();
        result = 31 * result + ingredient.hashCode();
        return result;
    }
}
