package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Ingredient;
import com.example.hiepphat.dtos.IngredientDTO;
import com.example.hiepphat.repositories.IngredientRepository;
import com.jayway.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;
    @Override
    public IngredientDTO getIngredientByRecipe(long recipeID) {
        float totalProtein=0;
        float totalCalo=0;
        float totalFat=0;
        float totalCarb=0;
       IngredientDTO result=new IngredientDTO();
        List<Ingredient> entites=ingredientRepository.getIngredient(recipeID);
        for(Ingredient item:entites) {
            String querry = item.getIngredient_name();
            final String uri = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=dIiAPHLfEenzJusaaqLPNvS4F3x9pjKxhteiR6ft&query=" + querry + "&pageSize=1&pageNumber=1";
            RestTemplate restTemplate = new RestTemplate();
            String resultJson = restTemplate.getForObject(uri, String.class);
            float protein = JsonPath.from(resultJson).get("foods.foodNutrients[0].value[0]");
            float fat = JsonPath.from(resultJson).get("foods.foodNutrients[0].value[1]");
            float carb = JsonPath.from(resultJson).get("foods.foodNutrients[0].value[2]");
            String calories = JsonPath.from(resultJson).get("foods.foodNutrients[0].value[3]").toString();
            totalProtein+=protein;
            totalCalo+=Float.parseFloat(calories);
            totalCarb+=carb;
            totalFat+=fat;
        }
        result.setCarb(totalCarb);
        result.setFat(totalFat);
        result.setProtein(totalProtein);
        result.setCalories(totalCalo);
        return result;
    }



}
