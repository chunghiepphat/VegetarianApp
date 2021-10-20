package com.example.hiepphat.Controller;
import com.example.hiepphat.Entity.*;
import com.example.hiepphat.dtos.*;
import com.example.hiepphat.repositories.*;
import com.example.hiepphat.request.RecipeRequest;
import com.example.hiepphat.response.*;
import com.example.hiepphat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private CommentRecipeServiceImpl commentRecipeService;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeCategoriesRepository recipeCategoriesRepository;
    @Autowired
    private RecipeStepRepository recipeStepRepository;
    @Autowired
    private RecipeStepServiceImpl recipeStepService;
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;
    //chức năng get all các recipe có phân trang (page: vị trí trang, limit: số record mong muốn trong 1 trang)
    @GetMapping("/getall")
    public RecipeResponse showRecipes(@RequestParam("page") int page,@RequestParam("limit") int limit){
        RecipeResponse result=new RecipeResponse();
        result.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result.setListResult(recipeService.findAll(pageable));
        result.setTotalPage((int)Math.ceil((double)recipeService.totalItem()/limit ));
        return result;
    }
    // chức năng get 10 recipe mới nhất theo time
    @GetMapping("/get10recipes")
    public TenRecipesResponse show10Recipes(){
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10Records());
        return result;
    }
    // chức năng get chi tiết 1 recipe theo recipe id
    @GetMapping("/getrecipeby/{id}")
    public RecipeDTO showRecipesbyID(@PathVariable long id) throws Exception {
        RecipeDTO result=recipeService.findrecipebyID(id);
        NutritionDTO nutrition=ingredientService.getIngredientByRecipe(id);
        result.setIngredients(recipeIngredientService.findByRecipe_RecipeID(id));
        result.setNutrition(nutrition);
        result.setTotalLike(recipeService.totalLike(id));
        result.setSteps(recipeStepService.findByRecipe_RecipeID(id));
        return result;
    }
    // chức năng get 10 recipe mới nhất của 1 user dựa theo user id
    @GetMapping("/get10recipebyuser/{id}")
    public TenRecipesResponse show10RecipesbyUserID(@PathVariable int id) throws Exception {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10ByUserOrderByTimeDesc(id));
        return result;
    }
    // chức năng get tất cả recipe của 1 user dựa theo user id ( có phân trang  )
    @GetMapping("/getallbyuserID/{id}")
    public RecipeResponse showRecipebyID(@RequestParam("page") int page, @RequestParam("limit") int limit, @PathVariable int id){
        RecipeResponse result2=new RecipeResponse();
        result2.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result2.setListResult(recipeService.findAllByUser_UserID(pageable,id));
        result2.setTotalPage((int)Math.ceil((double)recipeService.countByUser_UserID(id)/limit ));
        return result2;
    }
    //get 5 recipe nhiều like nhất
    @GetMapping("/get5bestrecipes")
    public TenRecipesResponse getbestrecipe() {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(likeRecipeService.findbestRecipe());
        return result;
    }
// tạo recipe
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
                            Recipe recipe=new Recipe();
                            User user=new User();
                            user.setUserID(recipeRequest.getUser_id());
                            recipe.setUser(user);
                            List<StepRecipeDTO>listStep=recipeRequest.getSteps();
                            recipe.setRecipeTitle(recipeRequest.getRecipe_title());
                            recipe.setRecipe_thumbnail(recipeRequest.getRecipe_thumbnail());
                         RecipeCategories recipeCategories=new RecipeCategories();
                          recipeCategories.setRecipeCategoryID(recipeRequest.getRecipe_categories_id());
                            recipe.setRecipeCategories(recipeCategories);
                            recipe.setBaking_time_minutes(recipeRequest.getBaking_time_minutes());
                            recipe.setPortion_size(recipeRequest.getPortion_size());
                            recipe.setPortion_type(recipeRequest.getPortion_type());
                            recipe.setPrep_time_minutes(recipeRequest.getPrep_time_minutes());
                            Calendar calendar = Calendar.getInstance();
                            java.util.Date currentTime = calendar.getTime();
                            recipe.setTime(currentTime);
                            recipe.setRecipe_difficulty(recipeRequest.getRecipe_difficulty());
                            recipe.setResting_time_minutes(recipeRequest.getResting_time_minutes());
                            recipe.setTotalCalo(0);
                            recipeService.save(recipe);
                            for(StepRecipeDTO itemStep:listStep) {
                                RecipeStep recipeStep = new RecipeStep();
                                recipeStep.setRecipe(recipe);
                                recipeStep.setStepIndex(listStep.indexOf(itemStep));
                                recipeStep.setContent(itemStep.getStep_content());
                                recipeStepRepository.save(recipeStep);
                            }
                     List<IngredientRecipeDTO>ingredientlistDTO=recipeRequest.getIngredients();
                     for(IngredientRecipeDTO item:ingredientlistDTO){
                         Ingredient ingredient=new Ingredient();
                         if(!ingredientService.existsByIngredient_name(item.getIngredient_name())){
                             ingredient.setIngredientName(item.getIngredient_name());
                             ingredientService.save(ingredient);
                             NutritionDTO dto=ingredientService.findByIngredientName(item.getIngredient_name());
                             Ingredient ingredient1=ingredientService.findIngredientName(item.getIngredient_name());
                             ingredient1.setFat(dto.getFat());
                             ingredient1.setProtein(dto.getProtein());
                             ingredient1.setCarb(dto.getCarb());
                             ingredient1.setCalories(dto.getCalories());
                             ingredientService.save(ingredient);
                         }
                         else{
                             Ingredient ingredient1=ingredientService.findIngredientName(item.getIngredient_name());
                             NutritionDTO dto=ingredientService.findByIngredientName(item.getIngredient_name());
                             ingredient1.setCalories(dto.getCalories());
                             ingredient1.setCarb(dto.getCarb());
                             ingredient1.setProtein(dto.getProtein());
                             ingredient1.setFat(dto.getFat());
                             ingredientService.save(ingredient1);
                         }
                         RecipeIngredient recipeIngredient=new RecipeIngredient();
                         int ingreID=ingredientService.findIngredientID(item.getIngredient_name());
                         Ingredient ingredient1= new Ingredient();
                         ingredient1.setIngredientID(ingreID);
                         recipeIngredient.setRecipe(recipe);
                         recipeIngredient.setIngredient(ingredient1);
                         recipeIngredient.setAmount(item.getAmount_in_mg());
                         recipeIngredientService.save(recipeIngredient);
                         NutritionDTO nutrition=ingredientService.getIngredientByRecipe(recipe.getRecipeID());
                         int totalCalo=(int)nutrition.getCalories();
                         Recipe updateRecipe=recipeRepository.findByRecipeID(recipe.getRecipeID());
                         updateRecipe.setTotalCalo(totalCalo);
                         recipeService.save(updateRecipe);
                     }


             return ResponseEntity.ok(new MessageResponse("Add recipe successfully!!!"));
        }
// view tất cả các categories của recipe
    @GetMapping("/categories")
    public RecipeCategoriesResponse getAllCategory() {
        RecipeCategoriesResponse result=new RecipeCategoriesResponse();
        result.setListResult(recipeService.getAllRecipeCategory());
        return result;
    }
    //delete 1 recipe(delete cả comment , like của recipe đó)
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteRecipe(@PathVariable("id")long id){
        recipeService.deleteLike(id);
        recipeIngredientService.deleteRecipeIngre(id);
        commentRecipeService.deleteComment(id);
        recipeStepService.deleteRecipeStep(id);
        recipeService.deleteByRecipeID(id);
        return ResponseEntity.ok(new MessageResponse("Delete Successfuly!!!"));
    }
    // like 1 recipe
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("like")
    public ResponseEntity<?> likeRecipe(@RequestBody LikeRecipeDTO dto){

        LikeRecipe likeRecipe=likeRecipeService.findByRecipe_RecipeIDAndUser_UserID(dto.getRecipe_id(),dto.getUser_id());
        if(likeRecipe!=null){
            likeRecipeRepository.delete(likeRecipe);
            return ResponseEntity.ok(new MessageResponse("Unlike"));
        }
        else{
            LikeRecipe newLike=new LikeRecipe();
            User user=new User();
            user.setUserID(dto.getUser_id());
            Recipe recipe=new Recipe();
            recipe.setRecipeID(dto.getRecipe_id());
            newLike.setUser(user);
            newLike.setRecipe(recipe);
            likeRecipeRepository.save(newLike);
            return ResponseEntity.ok(new MessageResponse("Liked"));
        }
    }
    // view tất cả comment của 1 recipe dựa theo recipe id
    @GetMapping("/{id}/comments")
    public ListCommentRecipeResponse getListCommentRecipe(@PathVariable("id")long id){
        ListCommentRecipeResponse response=new ListCommentRecipeResponse();
        response.setListCommentRecipe(commentRecipeService.getAllCommentRecipe(id));
        return response;
    }
    // chuc nang hien listUser da like recipe va tong so like
    @GetMapping("/{id}/listuserlike")
    public ListUserLikeResponse viewListlikeRecipe(@PathVariable("id")long id){
        ListUserLikeResponse listUserLikeResponse=new ListUserLikeResponse();
        listUserLikeResponse.setListUserlike(likeRecipeService.viewListUserLike(id));
        listUserLikeResponse.setTotalLike(recipeRepository.totalLike(id));
        return listUserLikeResponse;
    }
// chuc nang tao recipe category
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/create/category")
    public ResponseEntity<?> createRecipeCategory(@RequestBody RecipeCategoriesDTO dto){
        recipeCategoriesRepository.saveRecipeCategory(dto.getCategory_name(),dto.getThumbnail());
        return ResponseEntity.ok(new MessageResponse("Create recipe category successfully!!!"));
    }
    //chuc nang delete recipe category
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/category/{id}")
    public ResponseEntity<?> deleteRecipeCategory(@PathVariable("id")int id){
        RecipeCategories recipeCategories=recipeCategoriesRepository.getRecipeCategories(id);
        if(recipeCategories!=null){
            recipeCategoriesRepository.deleteRecipeCategory(id);
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Not found recipe category id!!!"));
        }
        return ResponseEntity.ok(new MessageResponse("Delete recipe category successfully!!!"));
    }
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?>editRecipe(@RequestBody RecipeRequest dto,@PathVariable("id")long id) throws ParseException {
        Recipe recipe=recipeRepository.findByRecipeID(id);
        if(recipe!=null){
            RecipeCategories recipeCategories=new RecipeCategories();
            recipeCategories.setRecipeCategoryID(dto.getRecipe_categories_id());
            recipe.setRecipeCategories(recipeCategories);
            recipe.setRecipe_thumbnail(dto.getRecipe_thumbnail());
            recipe.setRecipe_difficulty(dto.getRecipe_difficulty());
            recipe.setPortion_type(dto.getPortion_type());
            recipe.setPortion_size(dto.getPortion_size());
            recipe.setPrep_time_minutes(dto.getPrep_time_minutes());
            recipe.setBaking_time_minutes(dto.getBaking_time_minutes());
            recipe.setResting_time_minutes(dto.getResting_time_minutes());
            List<RecipeStep>entity=recipeStepRepository.findByRecipe_RecipeID(id);
            for(RecipeStep items:entity){
                recipeStepRepository.delete(items);
            }
                for(StepRecipeDTO step:dto.getSteps()){
                    RecipeStep recipeStep=new RecipeStep();
                    recipeStep.setRecipe(recipe);
                    recipeStep.setStepIndex(dto.getSteps().indexOf(step));
                    recipeStep.setContent(step.getStep_content());
                    recipeStepRepository.save(recipeStep);
                }
            List<RecipeIngredient>oldIngredient=recipeIngredientRepository.findByRecipe_RecipeID(id);
            for(RecipeIngredient item2:oldIngredient){
                recipeIngredientRepository.delete(item2);
            }
            for(IngredientRecipeDTO newIngre:dto.getIngredients()){
                Ingredient ingredient=new Ingredient();
                if(!ingredientService.existsByIngredient_name(newIngre.getIngredient_name())){
                    ingredient.setIngredientName(newIngre.getIngredient_name());
                    ingredientService.save(ingredient);
                    NutritionDTO nutriDTO=ingredientService.findByIngredientName(newIngre.getIngredient_name());
                    Ingredient ingredient1=ingredientService.findIngredientName(newIngre.getIngredient_name());
                    ingredient1.setFat(nutriDTO.getFat());
                    ingredient1.setProtein(nutriDTO.getProtein());
                    ingredient1.setCarb(nutriDTO.getCarb());
                    ingredient1.setCalories(nutriDTO.getCalories());
                    ingredientService.save(ingredient);
                }
                else{
                    Ingredient ingredient1=ingredientService.findIngredientName(newIngre.getIngredient_name());
                    NutritionDTO nutriDTO=ingredientService.findByIngredientName(newIngre.getIngredient_name());
                    ingredient1.setCalories(nutriDTO.getCalories());
                    ingredient1.setCarb(nutriDTO.getCarb());
                    ingredient1.setProtein(nutriDTO.getProtein());
                    ingredient1.setFat(nutriDTO.getFat());
                    ingredientService.save(ingredient1);
                }
                int ingreID=ingredientService.findIngredientID(newIngre.getIngredient_name());
                ingredient.setIngredientID(ingreID);
                RecipeIngredient recipeIngredient=new RecipeIngredient();
                recipeIngredient.setRecipe(recipe);
                recipeIngredient.setIngredient(ingredient);
                recipeIngredient.setAmount(newIngre.getAmount_in_mg());
                recipeIngredientRepository.save(recipeIngredient);
            }
            NutritionDTO nutrition=ingredientService.getIngredientByRecipe(id);
            int totalCalo=(int)nutrition.getCalories();
            recipe.setTotalCalo(totalCalo);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            String spf=simpleDateFormat.format(date);
            recipe.setTimeUpdated(simpleDateFormat.parse(spf));
            recipeRepository.save(recipe);
            return ResponseEntity.ok(new MessageResponse("Update recipe successfully!!!"));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Nout found recipe ID "+id));
        }
    }
    }


