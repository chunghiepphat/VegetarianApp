package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.response.RecipeResponse;
import com.example.hiepphat.response.TenRecipesResponse;
import com.example.hiepphat.service.Converter;
import com.example.hiepphat.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private Converter converter;
    @GetMapping("/getall")
    public RecipeResponse showRecipes(@RequestParam("page") int page,@RequestParam("limit") int limit){
        RecipeResponse result=new RecipeResponse();
        result.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result.setListResult(recipeService.findAll(pageable));
        result.setTotalPage((int)Math.ceil((double)recipeService.totalItem()/limit ));
        return result;
    }
    @GetMapping("/get10recipes")
    public TenRecipesResponse show10Recipes(){
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10Records());
        return result;
    }
    @GetMapping("/getrecipeby/{id}")
    public RecipeDTO showRecipesbyID(@PathVariable long id) throws Exception {
        RecipeDTO result=recipeService.findrecipebyID(id);
        if(result==null){
           throw new Exception("Nout found recipe id:"+ id);
        }
        return result;
    }
}
