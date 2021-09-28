package com.example.hiepphat.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Recipes_Ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    @Column(name = "amount_in_mg")
    private int amountInmg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmountInmg() {
        return amountInmg;
    }

    public void setAmountInmg(int amountInmg) {
        this.amountInmg = amountInmg;
    }
}
