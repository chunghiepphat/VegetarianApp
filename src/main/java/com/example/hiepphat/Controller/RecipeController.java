package com.example.hiepphat.Controller;

import com.example.hiepphat.response.RecipeResponse;
import com.example.hiepphat.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @GetMapping("/getall")
    public RecipeResponse showRecipes(@RequestParam("page") int page,@RequestParam("limit") int limit){
        RecipeResponse result=new RecipeResponse();
        result.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result.setListResult(recipeService.findAll(pageable));
        result.setTotalPage((int)Math.ceil((double)recipeService.totalItem()/limit ));
        return result;
    }
}
