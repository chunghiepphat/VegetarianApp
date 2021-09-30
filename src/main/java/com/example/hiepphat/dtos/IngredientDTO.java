package com.example.hiepphat.dtos;

public class IngredientDTO {
    private String ingredient_name;
    private int amount_in_mg;

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getAmount_in_mg() {
        return amount_in_mg;
    }

    public void setAmount_in_mg(int amount_in_mg) {
        this.amount_in_mg = amount_in_mg;
    }
}
