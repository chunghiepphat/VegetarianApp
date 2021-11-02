package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Ingredient;
import com.example.hiepphat.dtos.NutritionDTO;
import com.example.hiepphat.repositories.IngredientRepository;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;
    @Override
    public NutritionDTO getIngredientByRecipe(long recipeID) {
        float totalProtein=0;
        float totalCalo=0;
        float totalFat=0;
        float totalCarb=0;
       NutritionDTO result=new NutritionDTO();
        List<Ingredient> entites=ingredientRepository.getIngredient(recipeID);
        List<Integer> listInt=ingredientRepository.getAmount(recipeID);
          for(int i=0;i<listInt.size();i++) {
              for (Ingredient item : entites) {
                  if(item.getCalories()==null && item.getCarb()==null && item.getFat()==null &&item.getProtein()==null){
                      totalProtein+=0;
                      totalCalo+=0;
                      totalCarb+=0;
                      totalFat +=0;
                  }
                  else {
                      float protein1 = (item.getProtein() * listInt.get(i)) / 100;
                      float fat1 = (item.getFat() * listInt.get(i)) / 100;
                      float carb1 = (item.getCarb() * listInt.get(i)) / 100;
                      float calo1 = (item.getCalories() * listInt.get(i)) / 100;
                      totalProtein += protein1;
                      totalCalo += calo1;
                      totalCarb += carb1;
                      totalFat += fat1;
                  }
                 i++;
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

    @Override
    public NutritionDTO findByIngredientName(String name) {
        NutritionDTO result=new NutritionDTO();

        float protein1=0;
        float calo1=0;
        float fat1=0;
        float carb1=0;
        final String uri = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=dIiAPHLfEenzJusaaqLPNvS4F3x9pjKxhteiR6ft&query=" + name + "&pageSize=1&pageNumber=1";
        RestTemplate restTemplate = new RestTemplate();
        String resultJson = restTemplate.getForObject(uri, String.class);
        net.minidev.json.JSONArray protein = JsonPath.read(resultJson, "foods[0].foodNutrients[?(@.nutrientName == 'Protein')].value");
        net.minidev.json.JSONArray fat = JsonPath.read(resultJson, "foods[0].foodNutrients[?(@.nutrientName == 'Total lipid (fat)')].value");
        net.minidev.json.JSONArray carb = JsonPath.read(resultJson, "foods[0].foodNutrients[?(@.nutrientName == 'Carbohydrate, by difference')].value");
        net.minidev.json.JSONArray calories = JsonPath.read(resultJson, "foods[0].foodNutrients[?(@.nutrientName == 'Energy')].value");
        if (protein.isEmpty() && fat.isEmpty() && carb.isEmpty() && calories.isEmpty()) {
            protein.add(0, Float.valueOf(0));
            fat.add(0, Float.valueOf(0));
            carb.add(0, Float.valueOf(0));
            calories.add(0, Float.valueOf(0));
            protein1= Float.parseFloat(protein.get(0).toString());
            calo1 = Float.parseFloat(calories.get(0).toString());
            carb1 = Float.parseFloat(carb.get(0).toString());
            fat1= Float.parseFloat(fat.get(0).toString());
        } else {
            protein1 = (Float.parseFloat(protein.get(0).toString()));
            fat1 = (Float.parseFloat(fat.get(0).toString()));
            carb1 = (Float.parseFloat(carb.get(0).toString()));
            calo1 = (Float.parseFloat(calories.get(0).toString()));
        }
        result.setCarb(carb1);
        result.setFat(fat1);
        result.setProtein(protein1);
        result.setCalories(calo1);
        return result;
    }


    @Override
    public Ingredient findIngredientName(String name) {
        return ingredientRepository.findByIngredientName(name);
    }


}
