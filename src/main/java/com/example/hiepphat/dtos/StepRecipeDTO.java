package com.example.hiepphat.dtos;

public class StepRecipeDTO {
    private long recipe_id;
    private int step_index;
    private String step_content;

    public long getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getStep_index() {
        return step_index;
    }

    public void setStep_index(int step_index) {
        this.step_index = step_index;
    }

    public String getStep_content() {
        return step_content;
    }

    public void setStep_content(String step_content) {
        this.step_content = step_content;
    }
}
