package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private long ingredientID;
    private String ingredientName;
    @ManyToMany(mappedBy = "ingredients", cascade =CascadeType.ALL)
    private Set<Recipe> recipes=new HashSet<>();
    public long getIngredientID() {
        return ingredientID;
    }
    public void setIngredientID(long ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
