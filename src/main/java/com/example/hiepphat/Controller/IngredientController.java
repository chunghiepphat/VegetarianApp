package com.example.hiepphat.Controller;

import com.example.hiepphat.response.IngredientResponse;
import com.example.hiepphat.service.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    IngredientServiceImpl ingredientService;
    @GetMapping("/recipe/{id}")
    public IngredientResponse getIngredientByRecipe(@PathVariable int id) throws Exception {
        IngredientResponse result=new IngredientResponse();
        result.setNutrition(ingredientService.getIngredientByRecipe(id));
        return result;
    }
}
