package com.example.hiepphat.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RecipeCategories")
public class RecipeCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_category_id")
    private int recipeCategoryID;
    @Column(name = "recipe_category_name")
    private String recipeCategoryName;
    @Column(name = "recipe_category_thumbnail")
    private String recipeCategoryThumbnail;
    @OneToMany(mappedBy = "recipeCategories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> recipes;
    public int getRecipeCategoryID() {
        return recipeCategoryID;
    }

    public void setRecipeCategoryID(int recipeCategoryID) {
        this.recipeCategoryID = recipeCategoryID;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getRecipeCategoryName() {
        return recipeCategoryName;
    }

    public void setRecipeCategoryName(String recipeCategoryName) {
        this.recipeCategoryName = recipeCategoryName;
    }

    public String getRecipeCategoryThumbnail() {
        return recipeCategoryThumbnail;
    }

    public void setRecipeCategoryThumbnail(String recipeCategoryThumbnail) {
        this.recipeCategoryThumbnail = recipeCategoryThumbnail;
    }
}
