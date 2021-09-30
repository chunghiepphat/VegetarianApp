package com.example.hiepphat.response;

import com.example.hiepphat.dtos.NutritionDTO;

public class IngredientResponse {
    private NutritionDTO nutrition;

    public NutritionDTO getNutrition() {
        return nutrition;
    }

    public void setNutrition(NutritionDTO nutrition) {
        this.nutrition = nutrition;
    }
}
