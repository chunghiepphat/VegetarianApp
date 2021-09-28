package com.example.hiepphat.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private long ingredientID;
    private String ingredient_name;

    public long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(long ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }
}
