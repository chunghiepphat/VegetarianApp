package com.example.hiepphat.Controller;

import com.example.hiepphat.response.IngredientResponse;
import com.example.hiepphat.response.SearchResponse;
import com.example.hiepphat.service.BlogServiceImpl;
import com.example.hiepphat.service.IngredientServiceImpl;
import com.example.hiepphat.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/home")
public class HomeController {
@Autowired
private BlogServiceImpl blogService;
@Autowired
private RecipeServiceImpl recipeService;
    @GetMapping("/find")
    public SearchResponse searchRecipe(@RequestParam("search") String search) {
        SearchResponse result=new SearchResponse();
        result.setListRecipe(recipeService.findAllByRecipeTitleLike(search));
        result.setListBlog(blogService.findByBlog_titleLike(search));
        return result;
    }
}
