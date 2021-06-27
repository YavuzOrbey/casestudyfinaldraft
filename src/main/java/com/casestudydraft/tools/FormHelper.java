package com.casestudydraft.tools;

import com.casestudydraft.model.Ingredient;
import com.casestudydraft.model.Nutrient;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FormHelper {
    @Valid
    private Ingredient ingredient;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    @Valid
    private List<KeyValuePair<Nutrient, Integer>> nutrientList = new ArrayList<>();

    public List<KeyValuePair<Nutrient, Integer>> getNutrientList() {
        return nutrientList;
    }

    public void setNutrientList(List<KeyValuePair<Nutrient, Integer>> nutrientList) {
        this.nutrientList = nutrientList;
    }

    private List<KeyValuePair<String, String>> nutrientAmounts = new ArrayList<>();

    public List<KeyValuePair<String, String>> getNutrientAmounts() {
        return nutrientAmounts;
    }

    public void setNutrientAmounts(List<KeyValuePair<String, String>> nutrientAmounts) {
        this.nutrientAmounts = nutrientAmounts;
    }
    @Override
    public String toString() {
        return "FormHelper{\n" +
                "ingredient=\n" + ingredient +
                "\n, nutrientList=\n" + nutrientList +
                "\n}";
    }
}
