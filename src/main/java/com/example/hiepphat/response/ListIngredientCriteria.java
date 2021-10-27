package com.example.hiepphat.response;

import java.util.ArrayList;
import java.util.List;

public class ListIngredientCriteria {
    private List<IngredientCriteria>listIngredient=new ArrayList<>();

    public List<IngredientCriteria> getListIngredient() {
        return listIngredient;
    }

    public void setListIngredient(List<IngredientCriteria> listIngredient) {
        this.listIngredient = listIngredient;
    }
}
