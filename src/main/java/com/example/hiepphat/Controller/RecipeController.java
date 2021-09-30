package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.*;
import com.example.hiepphat.dtos.IngredientDTO;
import com.example.hiepphat.dtos.IngredientRecipeDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.repositories.LikeRecipeRepository;
import com.example.hiepphat.request.RecipeRequest;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.response.*;
import com.example.hiepphat.service.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private LikeRecipeRepository likeRecipeRepository;
    @Autowired
    private RecipeServiceImpl recipeService;
    @Autowired
    private Converter converter;
    @Autowired
    private LikeRecipeService likeRecipeService;
    @Autowired
    private IngredientServiceImpl ingredientService;
    @Autowired
    private RecipeIngredientServiceImpl recipeIngredientService;
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
        IngredientDTO nutrition=ingredientService.getIngredientByRecipe(id);
        result.setNutrition(nutrition);
        return result;
    }
    @GetMapping("/get10recipebyuser/{id}")
    public TenRecipesResponse show10RecipesbyUserID(@PathVariable int id) throws Exception {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10ByUserOrderByTimeDesc(id));
        return result;
    }
    @GetMapping("/getallbyuserID/{id}")
    public RecipeResponse showRecipebyID(@RequestParam("page") int page, @RequestParam("limit") int limit, @PathVariable int id){
        RecipeResponse result2=new RecipeResponse();
        result2.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result2.setListResult(recipeService.findAllByUser_UserID(pageable,id));
        result2.setTotalPage((int)Math.ceil((double)recipeService.countByUser_UserID(id)/limit ));
        return result2;
    }
    @GetMapping("/get5bestrecipes")
    public TenRecipesResponse getbestrecipe() {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(likeRecipeService.findbestRecipe());
        return result;
    }
    @GetMapping("/find")
    public TenRecipesResponse searchRecipe(@RequestParam("search") String search) {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findAllByRecipeTitleLike(search));
        return result;
    }
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
                            Recipe recipe=new Recipe();
                            User user=new User();
                            user.setUserID(recipeRequest.getUser_id());
                            recipe.setUser(user);
                            recipe.setRecipe_content(recipeRequest.getRecipe_content());
                            recipe.setRecipeTitle(recipeRequest.getRecipe_title());
                            recipe.setRecipe_thumbnail(recipeRequest.getRecipe_thumbnail());
                         RecipeCategories recipeCategories=new RecipeCategories();
                          recipeCategories.setRecipe_category_id(recipeRequest.getRecipe_categories_id());
                            recipe.setRecipeCategories(recipeCategories);
                            recipe.setBaking_time_minutes(recipeRequest.getBaking_time_minutes());
                            recipe.setPortion_size(recipeRequest.getPortion_size());
                            recipe.setPortion_type(recipeRequest.getPortion_type());
                            recipe.setPrep_time_minutes(recipeRequest.getPrep_time_minutes());
                            Date date=new Date(new java.util.Date().getTime());
                            recipe.setTime(date);
                            recipe.setRecipe_difficulty(recipeRequest.getRecipe_difficulty());
                            recipe.setResting_time_minutes(recipeRequest.getResting_time_minutes());
//                            Set<Ingredient>ingredientSet=new HashSet<>();
//                            Ingredient ingredient=new Ingredient();
//                            if(!ingredientService.existsByIngredient_name(recipeRequest.getIngredient_name())){
//                                ingredient.setIngredientName(recipeRequest.getIngredient_name());
//                                ingredientSet.add(ingredient);
//                                ingredientService.save(ingredient);
//                            }
//                            recipe.setIngredients(ingredientSet);
                     recipeService.save(recipe);
                     List<IngredientRecipeDTO>ingredientlistDTO=recipeRequest.getIngredients();
                     for(IngredientRecipeDTO item:ingredientlistDTO){
                         Ingredient ingredient=new Ingredient();
                         if(!ingredientService.existsByIngredient_name(item.getIngredient_name())){
                             ingredient.setIngredientName(item.getIngredient_name());
                             ingredientService.save(ingredient);
                         }
                         RecipeIngredient recipeIngredient=new RecipeIngredient();
                         int recipeID=recipeService.findrecipeID(recipeRequest.getRecipe_title(),recipeRequest.getUser_id());
                         Recipe recipe1=new Recipe();
                         recipe1.setRecipeID(recipeID);
                         int ingreID=ingredientService.findIngredientID(item.getIngredient_name());
                         Ingredient ingredient1= new Ingredient();
                         ingredient1.setIngredientID(ingreID);
                         recipeIngredient.setRecipe(recipe1);
                         recipeIngredient.setIngredient(ingredient1);
                         recipeIngredient.setAmount(item.getAmount_in_mg());
                         recipeIngredientService.save(recipeIngredient);
                     }

             return ResponseEntity.ok(new MessageResponse("Add recipe successfully!!!"));
        }
    @GetMapping("/categories")
    public RecipeCategoriesResponse getAllCategory() {
        RecipeCategoriesResponse result=new RecipeCategoriesResponse();
        result.setListResult(recipeService.getAllRecipeCategory());
        return result;
    }
    }


