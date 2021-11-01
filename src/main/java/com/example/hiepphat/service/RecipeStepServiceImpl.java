package com.example.hiepphat.service;

import com.example.hiepphat.Entity.RecipeStep;
import com.example.hiepphat.dtos.StepRecipeDTO;
import com.example.hiepphat.repositories.RecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RecipeStepServiceImpl implements RecipeStepService {
    @Autowired
    RecipeStepRepository recipeStepRepository;
    @Override
    public List<StepRecipeDTO> findByRecipe_RecipeID(long id) {
        List<StepRecipeDTO>result=new ArrayList<>();
        List<RecipeStep>entity=recipeStepRepository.findByRecipe_RecipeID(id);
        for(RecipeStep item:entity){
            StepRecipeDTO dto=new StepRecipeDTO();
            dto.setRecipe_id(item.getRecipe().getRecipeID());
            dto.setStep_content(item.getContent());
            dto.setStep_index(item.getStepIndex());
            result.add(dto);
        }
        return result;
    }

    @Override
    public void deleteRecipeStep(long id) {
        List<RecipeStep>entity=recipeStepRepository.findByRecipe_RecipeID(id);
        recipeStepRepository.deleteAll(entity);
    }
}
