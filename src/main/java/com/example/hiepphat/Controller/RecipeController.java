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
    @Autowired
    private UserTendencyRepository userTendencyRepository;
    @Autowired
    private UserPreferencesRepository userPreferencesRepository;
    @Autowired
    private UserBehaviorRepository userBehaviorRepository;
    @Autowired
    private UserAllergiesRepository userAllergiesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    //chức năng get all các recipe có phân trang (page: vị trí trang, limit: số record mong muốn trong 1 trang)
    @GetMapping("/getall")
    public RecipeResponse showRecipes(@RequestParam("page") int page,@RequestParam("limit") int limit){
        RecipeResponse result=new RecipeResponse();
        result.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("timeUpdated").descending().and(Sort.by("time")).descending());
        result.setListResult(recipeService.findAll(pageable));
        result.setTotalPage((int)Math.ceil((double)recipeService.totalItem()/limit));
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
        if(result!=null){
            NutritionDTO nutrition=ingredientService.getIngredientByRecipe(id);
            result.setIngredients(recipeIngredientService.findByRecipe_RecipeID(id));
            result.setNutrition(nutrition);
            result.setTotalLike(recipeService.totalLike(id));
            result.setSteps(recipeStepService.findByRecipe_RecipeID(id));
        }
        return result;
    }
    // chức năng get 10 recipe mới nhất của 1 user dựa theo user id( góc nhìn user id chủ profile)
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/get10recipebyuser/{id}")
    public TenRecipesResponse show10RecipesbyUserID(@PathVariable int id) throws Exception {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10ByUserOrderByTimeDesc(id));
        return result;
    }
    // chức năng get tất cả recipe của 1 user dựa theo user id ( có phân trang,góc nhìn user id chủ profile)  )
    @PreAuthorize("hasAuthority('user')")
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
                            recipe.setStatus(1);
                            recipe.setPrivate(recipeRequest.isIs_private());
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
            List<RecipeIngredient>recipeIngredients=recipeIngredientRepository.findByRecipe_RecipeID(dto.getRecipe_id());
            for(RecipeIngredient item:recipeIngredients){
                Ingredient ingredient=new Ingredient();
                ingredient.setIngredientID(item.getIngredient().getIngredientID());
                UserTendency userTendency=userTendencyRepository.findByUser_UserIDAndIngredient_IngredientID(dto.getUser_id(),ingredient.getIngredientID());
                if(userTendency.getFrequency()==1){
                    userTendencyRepository.delete(userTendency);
                }
                else{
                    userTendency.setFrequency(userTendency.getFrequency()-1);
                    userTendencyRepository.save(userTendency);
                }
            }
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
            List<RecipeIngredient>recipeIngredients=recipeIngredientRepository.findByRecipe_RecipeID(dto.getRecipe_id());
            for(RecipeIngredient item:recipeIngredients){
                Ingredient ingredient=new Ingredient();
                ingredient.setIngredientID(item.getIngredient().getIngredientID());
                UserTendency userTendency1=userTendencyRepository.findByUser_UserIDAndIngredient_IngredientID(dto.getUser_id(),ingredient.getIngredientID());
                if(userTendency1!=null){
                    if(userTendency1.getFrequency()>=1){
                        userTendency1.setFrequency(userTendency1.getFrequency()+1);
                        userTendencyRepository.save(userTendency1);
                    }
                }
                else{
                    UserTendency userTendency=new UserTendency();
                    userTendency.setUser(user);
                    userTendency.setIngredient(ingredient);
                    userTendency.setFrequency(1);
                    userTendencyRepository.save(userTendency);
                }
            }
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
            recipe.setPrivate(dto.isIs_private());
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

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/suggestion/{id}")
    public List<SuggestionRecipeDTO> suggestRecipe(@PathVariable("id")int userID) throws InterruptedException {
            List<UserPreference>listPreference=userPreferencesRepository.findByUser_UserID(userID);
            List<List<SuggestionRecipeDTO>>listRecipeSuggest=new ArrayList<>();
            List<SuggestionRecipeDTO>listPrf=new ArrayList<>();
            List<SuggestionRecipeDTO>listTenden=new ArrayList<>();
            List<SuggestionRecipeDTO>listBeha=new ArrayList<>();
            List<SuggestionRecipeDTO>listBody=new ArrayList<>();
            List<TenRecipeDTO>listLiked=new ArrayList<>();
            List<SuggestionRecipeDTO>listmostLike=new ArrayList<>();
            for(UserPreference itemPrefer:listPreference){
                Ingredient recipePrefer=ingredientRepository.findByIngredientName(itemPrefer.getIngredientName());
                if(recipePrefer!=null){
                    List<RecipeIngredient>listRecipePrefer=recipeIngredientRepository.findByIngredient_IngredientID(recipePrefer.getIngredientID());
                    for(RecipeIngredient itemIngredientPrefer:listRecipePrefer){
                        SuggestionRecipeDTO dto=new SuggestionRecipeDTO();
                        dto.setRecipe_id(itemIngredientPrefer.getRecipe().getRecipeID());
                        dto.setRecipe_thumbnail(itemIngredientPrefer.getRecipe().getRecipe_thumbnail());
                        dto.setRecipe_title(itemIngredientPrefer.getRecipe().getRecipeTitle());
                        dto.setLast_name(itemIngredientPrefer.getRecipe().getUser().getLastName());
                        dto.setFirst_name(itemIngredientPrefer.getRecipe().getUser().getFirstName());
                        dto.setTime_created(itemIngredientPrefer.getRecipe().getTime());
                        dto.setTotalLike(recipeRepository.totalLike(itemIngredientPrefer.getRecipe().getRecipeID()));
                        dto.setUser_id(itemIngredientPrefer.getRecipe().getUser().getUserID());
                        dto.setCriteria("Preference");
                        listPrf.add(dto);
                    }
                }
            }
        for(int i=0;i< listPrf.size()-1;i++){
            for(int j=i+1;j<listPrf.size();j++){
                if(listPrf.get(i).getRecipe_id()==listPrf.get(j).getRecipe_id()){
                    listPrf.remove(j);
                }
            }
        }
            List<UserTendency>listTendency=userTendencyRepository.findByUser_UserIDAndFrequencyGreaterThanEqual(userID,5);
            for(UserTendency itemTendency:listTendency){
                List<RecipeIngredient>itemIngredientTendency=recipeIngredientRepository.findByIngredient_IngredientID(itemTendency.getIngredient().getIngredientID());
                for(RecipeIngredient recipeTendency:itemIngredientTendency){
                    SuggestionRecipeDTO dto=new SuggestionRecipeDTO();
                    dto.setRecipe_id(recipeTendency.getRecipe().getRecipeID());
                    dto.setRecipe_thumbnail(recipeTendency.getRecipe().getRecipe_thumbnail());
                    dto.setRecipe_title(recipeTendency.getRecipe().getRecipeTitle());
                    dto.setLast_name(recipeTendency.getRecipe().getUser().getLastName());
                    dto.setFirst_name(recipeTendency.getRecipe().getUser().getFirstName());
                    dto.setTime_created(recipeTendency.getRecipe().getTime());
                    dto.setTotalLike(recipeRepository.totalLike(recipeTendency.getRecipe().getRecipeID()));
                    dto.setUser_id(recipeTendency.getRecipe().getUser().getUserID());
                    dto.setCriteria("Tendency");
                    listTenden.add(dto);
                }
            }
        List<UserBehavior>listBehavior=userBehaviorRepository.findByUser_UserIDAndFrequencyGreaterThanEqual(userID,5);
        for(UserBehavior itemBehavior:listBehavior){
            List<Recipe>recipeBehavior=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+itemBehavior.getQuerry()+"%", "%"+itemBehavior.getQuerry()+"%","%"+ itemBehavior.getQuerry()+"%");
            for(Recipe behavior:recipeBehavior){
                SuggestionRecipeDTO dto=new SuggestionRecipeDTO();
                dto.setRecipe_id(behavior.getRecipeID());
                dto.setRecipe_thumbnail(behavior.getRecipe_thumbnail());
                dto.setRecipe_title(behavior.getRecipeTitle());
                dto.setLast_name(behavior.getUser().getLastName());
                dto.setFirst_name(behavior.getUser().getFirstName());
                dto.setTime_created(behavior.getTime());
                dto.setTotalLike(recipeRepository.totalLike(behavior.getRecipeID()));
                dto.setUser_id(behavior.getUser().getUserID());
                dto.setCriteria("Behavior");
                listBeha.add(dto);
            }
        }
        List<TenRecipeDTO>listnoSuggested=new ArrayList<>();
        List<UserAllergies>listAllergies=userAllergiesRepository.findByUser_UserID(userID);
        for(UserAllergies allergies:listAllergies){
            Ingredient ingreAllergies=ingredientRepository.findByIngredientName(allergies.getIngredientName());
            if(ingreAllergies!=null){
                List<RecipeIngredient>listRecipeAllergies=recipeIngredientRepository.findByIngredient_IngredientID(ingreAllergies.getIngredientID());
                for(RecipeIngredient itemAllergies:listRecipeAllergies){
                    TenRecipeDTO dto=new TenRecipeDTO();
                    dto.setRecipe_id(itemAllergies.getRecipe().getRecipeID());
                    dto.setRecipe_thumbnail(itemAllergies.getRecipe().getRecipe_thumbnail());
                    dto.setRecipe_title(itemAllergies.getRecipe().getRecipeTitle());
                    dto.setLast_name(itemAllergies.getRecipe().getUser().getLastName());
                    dto.setFirst_name(itemAllergies.getRecipe().getUser().getFirstName());
                    dto.setTime_created(itemAllergies.getRecipe().getTime());
                    dto.setTotalLike(recipeRepository.totalLike(itemAllergies.getRecipe().getRecipeID()));
                    listnoSuggested.add(dto);
                }
            }
        }
                for(int i=0;i< listnoSuggested.size()-1;i++){
            for(int j=i+1;j<listnoSuggested.size();j++){
                if(listnoSuggested.get(i).getRecipe_id()==listnoSuggested.get(j).getRecipe_id()){
                    listnoSuggested.remove(j);
                }
            }
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String spf=simpleDateFormat.format(date);
        User existUser=userRepository.findByUserID(userID);
        double caloNeed=0;
        if(existUser!=null){
            float r=0;
            if(existUser.getTypeWorkout()==1){
                r=Float.parseFloat("1.2");
            }
            else if(existUser.getTypeWorkout()==2){
                r=Float.parseFloat("1.375");
            }
            else if(existUser.getTypeWorkout()==3){
                r=Float.parseFloat("1.55");
            }
            else if(existUser.getTypeWorkout()==4){
                r=Float.parseFloat("1.725");
            }
            int currentDay=date.getYear()+1900;
            int yearUser=existUser.getBirth_date().getYear()+1900;
            int age=currentDay-yearUser;
            if(existUser.getGender().trim().equalsIgnoreCase("male")){
                caloNeed=((existUser.getWeight()*13.997)+(4.779*existUser.getHeight())-(5.677*age)+88.362)*r;
            }
            else if(existUser.getGender().trim().equalsIgnoreCase("female")){
                caloNeed=((existUser.getWeight()*9.247)+(3.098*existUser.getHeight())-(4.330*age)+447.593)*r;
            }
            System.out.println((int)caloNeed);
            double calo=caloNeed/3;
            System.out.println((int)calo);
            List<Recipe>listBodymatch=recipeRepository.findByTotalCaloLessThanEqualAndTotalCaloGreaterThan((int)calo,(int)calo-20);
            for(Recipe itemBodyMatch:listBodymatch){
                SuggestionRecipeDTO dto=new SuggestionRecipeDTO();
                dto.setRecipe_id(itemBodyMatch.getRecipeID());
                dto.setRecipe_thumbnail(itemBodyMatch.getRecipe_thumbnail());
                dto.setRecipe_title(itemBodyMatch.getRecipeTitle());
                dto.setLast_name(itemBodyMatch.getUser().getLastName());
                dto.setFirst_name(itemBodyMatch.getUser().getFirstName());
                dto.setTime_created(itemBodyMatch.getTime());
                dto.setTotalLike(recipeRepository.totalLike(itemBodyMatch.getRecipeID()));
                dto.setUser_id(itemBodyMatch.getUser().getUserID());
                dto.setCriteria("Body Match");
                listBody.add(dto);
            }
        }
        List<Recipe>listMost=recipeRepository.findLikeGreater2();
        for(Recipe itemMost:listMost){
            SuggestionRecipeDTO dto=new SuggestionRecipeDTO();
            dto.setRecipe_id(itemMost.getRecipeID());
            dto.setRecipe_thumbnail(itemMost.getRecipe_thumbnail());
            dto.setRecipe_title(itemMost.getRecipeTitle());
            dto.setLast_name(itemMost.getUser().getLastName());
            dto.setFirst_name(itemMost.getUser().getFirstName());
            dto.setTime_created(itemMost.getTime());
            dto.setTotalLike(recipeRepository.totalLike(itemMost.getRecipeID()));
            dto.setUser_id(itemMost.getUser().getUserID());
            dto.setCriteria("Most people like");
            listmostLike.add(dto);
        }
        List<LikeRecipe>listLike=likeRecipeRepository.findByUser_UserID(userID);
        for(LikeRecipe itemLiked:listLike){
            TenRecipeDTO dto=new TenRecipeDTO();
            dto.setRecipe_id(itemLiked.getRecipe().getRecipeID());
            dto.setRecipe_thumbnail(itemLiked.getRecipe().getRecipe_thumbnail());
            dto.setRecipe_title(itemLiked.getRecipe().getRecipeTitle());
            dto.setLast_name(itemLiked.getUser().getLastName());
            dto.setFirst_name(itemLiked.getUser().getFirstName());
            dto.setTime_created(itemLiked.getRecipe().getTime());
            dto.setTotalLike(recipeRepository.totalLike(itemLiked.getRecipe().getRecipeID()));
            listLiked.add(dto);
        }
        listRecipeSuggest.add(listPrf);
        listRecipeSuggest.add(listTenden);
        listRecipeSuggest.add(listBeha);
        listRecipeSuggest.add(listBody);
        listRecipeSuggest.add(listmostLike);
        List<SuggestionRecipeDTO>perfectList=new ArrayList<>();
            while(perfectList.size()<5){
                Random rand=new Random();
                int freq[]={40,15,15,10,20};
                if(listPrf.size()==0&&listBeha.size()==0&&listTenden.size()==0){
                    int a[]={0,0,0,50,50};
                    freq=a.clone();
                }
                if(listPrf.size()==0&&listBeha.size()==0&&listTenden.size()==0&&listBody.size()==0){
                    int b[]={0,0,0,0,100};
                    freq=b.clone();
                }
                List<SuggestionRecipeDTO> ranNew=myRand(listRecipeSuggest,freq,listRecipeSuggest.size());
                int index2=rand.nextInt(ranNew.size());
                SuggestionRecipeDTO ranObject=ranNew.get(index2);
                perfectList.add(ranObject);
                for(int a=0;a<listnoSuggested.size();a++){
                    if(ranObject.getRecipe_id()==listnoSuggested.get(a).getRecipe_id()){
                        perfectList.remove(ranObject);
                    }
                }
                for(int c=0;c<listLiked.size();c++){
                    if(ranObject.getRecipe_id()==listLiked.get(c).getRecipe_id()){
                        perfectList.remove(ranObject);
                    }
                }
                for(int i=0;i<perfectList.size()-1;i++){
                    for(int j=i+1;j<perfectList.size();j++){
                        if(perfectList.get(i).getRecipe_id()==perfectList.get(j).getRecipe_id()){
                            perfectList.remove(j);
                        }
                    }
                }
            }
            return perfectList;
    }
    static int findCeil(int arr[], int r, int l, int h)
    {
        int mid;
        while (l < h)
        {
            mid = l + ((h - l) >> 1); // Same as mid = (l+h)/2
            if(r > arr[mid])
                l = mid + 1;
            else
                h = mid;
        }
        return (arr[l] >= r) ? l : -1;
    }

    // The main function that returns a random number
// from arr[] according to distribution array
// defined by freq[]. n is size of arrays.
    static List<SuggestionRecipeDTO> myRand(List<List<SuggestionRecipeDTO>>list, int freq[], int n)
    {
        // Create and fill prefix array
        int prefix[] = new int[n], i;
        prefix[0] = freq[0];
        for (i = 1; i < n; ++i)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number with
        // value from 1 to this sum
        int r = ((int)(Math.random()*(323567)) % prefix[n - 1]) + 1;

        // Find index of ceiling of r in prefix array
        int indexc = findCeil(prefix, r, 0, n - 1);
        return list.get(indexc);
    }
    //duyet bai
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/approve/{id}")
    public ResponseEntity<?>approveRecipe(@PathVariable("id")long id,@RequestBody RecipeDTO dto){
        Recipe recipe=recipeRepository.findByRecipeID(id);
         if(recipe!=null){
             recipe.setStatus(dto.getStatus());
             recipeRepository.save(recipe);
             return ResponseEntity.ok(new MessageResponse("Status recipe change successfully!!!"));
         }
        else{
             return ResponseEntity.badRequest().body(new MessageResponse("Not found recipe id "+id));
         }
    }
    //chuc nang hien 10 bai viet cua user ( góc nhìn 1 user khác)
    @GetMapping("/get10recipebyuserdifferent/{id}")
    public TenRecipesResponse show10RecipesbyUserIDOtherside(@PathVariable int id) throws Exception {
        TenRecipesResponse result=new TenRecipesResponse();
        result.setListResult(recipeService.findTop10ByUserOrderByTimeDescOtherside(id));
        return result;
    }
    //chuc nang hien tatca bai viet cua user có phân trang ( góc nhìn 1 user khác)
    @GetMapping("/getallbyuserIDdifferent/{id}")
    public RecipeResponse showRecipebyIDOtherside(@RequestParam("page") int page, @RequestParam("limit") int limit, @PathVariable int id){
        RecipeResponse result2=new RecipeResponse();
        result2.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result2.setListResult(recipeService.findAllByUser_UserID(pageable,id));
        result2.setTotalPage((int)Math.ceil((double)recipeService.countByUser_UserID(id)/limit ));
        return result2;
    }
    //chuc nang get all recipe cho admin

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/admin/getall")
    public RecipeResponse showallAdminRecipe(@RequestParam("page") int page,@RequestParam("limit") int limit){
        RecipeResponse result=new RecipeResponse();
        result.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("timeUpdated").descending().and(Sort.by("time")).descending());
        result.setListResult(recipeService.findAllAdmin(pageable));
        result.setTotalPage((int)Math.ceil((double)recipeService.totalItem()/limit));
        return result;
    }
    }


