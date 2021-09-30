package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Ingredient;
import com.example.hiepphat.dtos.IngredientDTO;
import com.example.hiepphat.repositories.IngredientRepository;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            String querry = item.getIngredientName();
            final String uri = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=dIiAPHLfEenzJusaaqLPNvS4F3x9pjKxhteiR6ft&query=" + querry + "&pageSize=1&pageNumber=1";
            RestTemplate restTemplate = new RestTemplate();
            String resultJson = restTemplate.getForObject(uri, String.class);
            JSONObject root=new JSONObject(resultJson);
            net.minidev.json.JSONArray protein = JsonPath.read(resultJson,"foods[0].foodNutrients[?(@.nutrientName == 'Protein')].value");
            net.minidev.json.JSONArray fat = JsonPath.read(resultJson,"foods[0].foodNutrients[?(@.nutrientName == 'Total lipid (fat)')].value");
            net.minidev.json.JSONArray carb =JsonPath.read(resultJson,"foods[0].foodNutrients[?(@.nutrientName == 'Carbohydrate, by difference')].value");
            net.minidev.json.JSONArray calories =JsonPath.read(resultJson,"foods[0].foodNutrients[?(@.nutrientName == 'Energy')].value");
            if(protein.isEmpty() && fat.isEmpty() && carb.isEmpty() && calories.isEmpty()){
                protein.add(0,Float.valueOf(0));
                fat.add(0,Float.valueOf(0));
                carb.add(0,Float.valueOf(0));
                calories.add(0,Float.valueOf(0));
                totalProtein+=Float.parseFloat(protein.get(0).toString());
                totalCalo+=Float.parseFloat(calories.get(0).toString());
                totalCarb+=Float.parseFloat(carb.get(0).toString());
                totalFat+=Float.parseFloat(fat.get(0).toString());
            }
            else{
                totalProtein+=Float.parseFloat(protein.get(0).toString());
                totalCalo+=Float.parseFloat(calories.get(0).toString());
                totalCarb+=Float.parseFloat(carb.get(0).toString());
                totalFat+=Float.parseFloat(fat.get(0).toString());
            }

        }
        result.setCarb(totalCarb);
        result.setFat(totalFat);
        result.setProtein(totalProtein);
        result.setCalories(totalCalo);
        return result;
    }

    @Override
    public boolean existsByIngredient_name(String name) {
        return ingredientRepository.existsByIngredientName(name);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public int findIngredientID(String name) {
        return ingredientRepository.findIngredientID(name);
    }


}
