package com.example.hiepphat.response;

import com.example.hiepphat.dtos.IngredientDTO;
import com.example.hiepphat.dtos.NutritionDTO;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {
    private IngredientDTO nutrition;

    public IngredientDTO getNutrition() {
        return nutrition;
    }

    public void setNutrition(IngredientDTO nutrition) {
        this.nutrition = nutrition;
    }
}
