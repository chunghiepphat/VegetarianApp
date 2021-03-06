package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.*;
import com.example.hiepphat.JWTUtils.JwtUtils;
import com.example.hiepphat.dtos.*;
import com.example.hiepphat.repositories.*;
import com.example.hiepphat.request.LoginRequest;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.request.UpdateUserRequest;
import com.example.hiepphat.response.*;
import com.example.hiepphat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
 @Autowired
    UserServiceImpl userService;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    JwtUtils jwtUtils;
@Autowired
UserRepository userRepository;
@Autowired
    CommentBlogServiceImpl commentBlogService;
@Autowired
    CommentRecipeServiceImpl commentRecipeService;
@Autowired
    RecipeServiceImpl recipeService;
@Autowired
    BlogServiceImpl blogService;
    @Autowired
    CommentBlogRepository commentBlogRepository;
    @Autowired
    CommentRecipeRepository commentRecipeRepository;
    @Autowired
    LikeRecipeService likeRecipeService;
    @Autowired
    LikeBlogService likeBlogService;
    @Autowired
    UserPreferencesRepository userPreferencesRepository;
    @Autowired
    UserAllergiesRepository userAllergiesRepository;
    //login
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        boolean checkActive=jwtUtils.getStatusUser(jwt);
        if(checkActive==false){
            return ResponseEntity.badRequest().body(new MessageResponse("Account is inactive!!!"));
        }
        else{
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
    }
//signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new SignupResponse("Error: Email is already taken!"));
        }
        else {
            Role roles = new Role();
            roles.setRole_id(1);
            // Create new user's account
            User user = new User(signUpRequest.getFirst_name(), signUpRequest.getLast_name(),
                    signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword())
                    , true, roles);
            user.setProvider("local");
            userService.save(user);
            String jwt=jwtUtils.generateJwtTokenSignup(signUpRequest);
            return ResponseEntity.ok(new SignupResponse(jwt));
        }
    }
//update user password
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/update/password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody UpdateUserRequest model, @PathVariable("id") int id){
             User oldUser=userService.findByUser_id(id);
             if(oldUser!=null) {
                 String oldPasswordDB=oldUser.getPassword();
                 String oldpassword=model.getOldPassword();
                 if(passwordEncoder.matches(oldpassword,oldPasswordDB)){
                     oldUser.setPassword(passwordEncoder.encode(model.getPassword()));
                     userService.save(oldUser);
                 }
                 else{
                     return ResponseEntity.badRequest().body(new MessageResponse("Old password not correct!!!"));
                 }
             }
         return ResponseEntity.ok(new MessageResponse("Update successfully!!!"));
    }
    //update user profile
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/update/profile/{id}")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateUserRequest model, @PathVariable("id") int id){
        User oldUser=userService.findByUser_id(id);
        if(oldUser!=null) {
            oldUser.setProfile_image(model.getProfile_image());
            userService.save(oldUser);
        }
        return ResponseEntity.ok(new MessageResponse("Update successfully!!!"));
    }
//update user detail
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/update/details/{id}")
    public ResponseEntity<?> updateDetail(@RequestBody UpdateUserRequest model, @PathVariable("id") int id){
        User oldUser=userService.findByUser_id(id);
        if(oldUser!=null) {
            oldUser.setFirstName(model.getFirst_name());
            oldUser.setLastName(model.getLast_name());
            oldUser.setGender(model.getGender());
            oldUser.setCountry(model.getCountry());
            oldUser.setAbout_me(model.getAbout_me());
            oldUser.setBirth_date(model.getBirth_date());
            oldUser.setPhone_number(model.getPhone_number());
            oldUser.setInstagram_link(model.getInstagram_link());
            oldUser.setFacebook_link(model.getFacebook_link());
            userService.save(oldUser);
        }
        return ResponseEntity.ok(new MessageResponse("Update successfully!!!"));
    }
    //update body index(height,weight,workout_rountine)
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/update/bodyindex/{id}")
    public ResponseEntity<?> updateBodyindex(@RequestBody UpdateUserRequest model, @PathVariable("id") int id){
        User oldUser=userService.findByUser_id(id);
        if(oldUser!=null) {
            oldUser.setHeight(model.getHeight());
            oldUser.setTypeWorkout(model.getWorkout_routine());
            oldUser.setWeight(model.getWeight());
            userService.save(oldUser);
        }
        return ResponseEntity.ok(new MessageResponse("Update successfully!!!"));
    }

    // chi ti???t th??ng tin c???a 1 user d???a theo user id
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id")int id) {
        User user=userRepository.findByUserID(id);
            if(user.isIs_active()==true){
                UserDTO dto=new UserDTO();
                dto.setId(user.getUserID());
                dto.setFirst_name(user.getFirstName());
                dto.setLast_name(user.getLastName());
                dto.setEmail(user.getEmail());
                dto.setCountry(user.getCountry());
                dto.setBirth_date(user.getBirth_date());
                dto.setGender(user.getGender());
                dto.setAbout_me(user.getAbout_me());
                dto.setFacebook_link(user.getFacebook_link());
                dto.setInstagram_link(user.getInstagram_link());
                dto.setPhone_number(user.getPhone_number());
                dto.setProfile_image(user.getProfile_image());
                dto.setRole(user.getRole().getRole_name());
                dto.setIs_active(user.isIs_active());
                dto.setWeight(user.getWeight());
                dto.setHeight(user.getHeight());
                dto.setWorkout_routine(user.getTypeWorkout());
                return dto;
            }
            else{
                return null;
            }
    }
//ch???c n??ng comment tr??n blog
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/commentblog")
    public CommentBlogDTO commentBlog(@RequestBody CommentBlogDTO dto) throws ParseException {
        CommentBlog commentBlog=new CommentBlog();
        Blog blog=new Blog();
        User user=new User();
        user.setUserID(dto.getUser_id());
        blog.setBlogID(dto.getBlog_id());
        commentBlog.setUser(user);
        commentBlog.setBlog(blog);
        commentBlog.setContent(dto.getContent());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String spf=simpleDateFormat.format(date);
        commentBlog.setTime(simpleDateFormat.parse(spf));
        dto.setTime(simpleDateFormat.parse(spf));
        User oldUser=userService.findByUser_id(dto.getUser_id());
        dto.setFirst_name(oldUser.getFirstName());
        dto.setLast_name(oldUser.getLastName());
        commentBlogService.save(commentBlog);
        CommentBlog currentBlog=commentBlogRepository.findByBlog_BlogIDAndUser_UserIDAndTime(dto.getBlog_id(),dto.getUser_id(),simpleDateFormat.parse(spf));
        dto.setId(currentBlog.getId());
        return dto;
    }
    // ch???c n??ng comment tr??n recipe
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/commentrecipe")
    public CommentRecipeDTO commentRecipe(@RequestBody CommentRecipeDTO dto) throws ParseException {
        CommentRecipe commentRecipe=new CommentRecipe();
        Recipe recipe=new Recipe();
        User user=new User();
        user.setUserID(dto.getUser_id());
        recipe.setRecipeID(dto.getRecipe_id());
        commentRecipe.setUser(user);
        commentRecipe.setRecipe(recipe);
        commentRecipe.setContent(dto.getContent());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String spf=simpleDateFormat.format(date);
        commentRecipe.setTime(simpleDateFormat.parse(spf));
        dto.setTime(simpleDateFormat.parse(spf));
        User oldUser=userService.findByUser_id(dto.getUser_id());
        dto.setFirst_name(oldUser.getFirstName());
        dto.setLast_name(oldUser.getLastName());
        commentRecipeService.save(commentRecipe);
        CommentRecipe currentComment=commentRecipeRepository.findByRecipe_RecipeIDAndUser_UserIDAndTime(dto.getRecipe_id(),dto.getUser_id(),simpleDateFormat.parse(spf));
        dto.setId(currentComment.getId());
        return dto;
    }
    //ch???c n??ng hi???n t???t c??? nh???ng recipe,blog m?? user id n??o ???? ???? like d???a theo user_id
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/{id}/liked")
    public ViewLikedResponse viewLiked(@PathVariable("id") int id){
        ViewLikedResponse viewLikedResponse=new ViewLikedResponse();
        viewLikedResponse.setListRecipe(recipeService.findLikedRecipe(id));
        viewLikedResponse.setListBlog(blogService.findLikedBlog(id));
        return viewLikedResponse;
    }

    //ch???c n??ng delete comment c???a blog d???a theo comment id
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/deleteComment/{commentID}/blog")
    public ResponseEntity<?>deletecommentBlog(@PathVariable("commentID")int commentID){
         commentBlogService.deleteUserCommentByID(commentID);
        return ResponseEntity.ok(new MessageResponse("Delete successfully!!!"));
    }
    // ch???c n??ng delete comment c???a recipe d???a theo comment id
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/deleteComment/{commentID}/recipe")
    public ResponseEntity<?>deletecommentRecipe(@PathVariable("commentID")int commentID){
        commentRecipeRepository.deleteById(commentID);
        return ResponseEntity.ok(new MessageResponse("Delete successfully!!!"));
    }
    //ch???c n??ng edit comment blog
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/edit/commentblog/{id}")
    public ResponseEntity<?> editCommentBlog(@RequestBody CommentBlogDTO dto,@PathVariable("id")int id) throws ParseException {
        CommentBlog commentBlog=commentBlogService.findById(id);
        if(commentBlog!=null){
            commentBlog.setContent(dto.getContent());
            commentBlogService.save(commentBlog);
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Comment ID nout found"));
        }
        return ResponseEntity.ok(new MessageResponse("Edit comment blog successfully!!!"));
    }
    //ch???c n??ng edit comment recipe
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/edit/commentrecipe/{id}")
    public ResponseEntity<?> editCommentRecipe(@RequestBody CommentRecipeDTO dto,@PathVariable("id")int id) throws ParseException {
        CommentRecipe commentRecipe=commentRecipeService.findById(id);
        if(commentRecipe!=null){
            commentRecipe.setContent(dto.getContent());
            commentRecipeService.save(commentRecipe);
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Comment ID nout found"));
        }
        return ResponseEntity.ok(new MessageResponse("Edit comment recipe successfully!!!"));
    }
    //check like blog
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/blog/islike")
    public LikeResponse checklikeBlog(@RequestParam("userID")int userID,@RequestParam("blogID")int blogID){
        LikeResponse likeResponse=new LikeResponse();
        LikeBlog likeBlog=likeBlogService.findByUser_UserIDAndBlog_BlogID(userID,blogID);
        if(likeBlog!=null){
            likeResponse.setIs_Liked(true);
            return likeResponse;
        }
        else{
           likeResponse.setIs_Liked(false);
            return likeResponse;
        }
    }
    //check like recipe
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/recipe/islike")
    public LikeResponse checklikeRecipe(@RequestParam("userID")int userID,@RequestParam("recipeID")long recipeID) {
        LikeResponse likeResponse=new LikeResponse();
        LikeRecipe likeRecipe = likeRecipeService.findByRecipe_RecipeIDAndUser_UserID(recipeID, userID);
        if (likeRecipe != null) {
            likeResponse.setIs_Liked(true);
            return likeResponse;
        }
        else {
            likeResponse.setIs_Liked(false);
            return likeResponse;
        }
    }
    //update ingredient ua thich dua theo user_id ,ingredient_id
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/preferences/{id}")
    public ResponseEntity<?>updatePreferences(@PathVariable("id")int userID, @RequestBody ListIngredientCriteria listIngredient){

            List<UserPreference>listPrefer=userPreferencesRepository.findByUser_UserID(userID);
            for(UserPreference item:listPrefer){
                userPreferencesRepository.delete(item);
            }
            for(IngredientCriteria item2:listIngredient.getListIngredient()){
                User user=new User();
                user.setUserID(userID);
                UserPreference userPreference=new UserPreference();
                userPreference.setUser(user);
                userPreference.setIngredientName(item2.getIngredient_name());
                userPreferencesRepository.save(userPreference);
            }
            return ResponseEntity.ok(new MessageResponse("Update preferences successfully!!!"));
    }
    //update ingredient di ung dua theo user_id ,ingredient_id
    @PreAuthorize("hasAuthority('user')")
    @PutMapping("/allergies/{id}")
    public ResponseEntity<?>updateAllergies(@PathVariable("id")int userID,@RequestBody ListIngredientCriteria listIngredient){
        List<UserAllergies>allergiesList=userAllergiesRepository.findByUser_UserID(userID);
        for(UserAllergies item:allergiesList){
            userAllergiesRepository.delete(item);
        }
        for(IngredientCriteria item2:listIngredient.getListIngredient()){
            User user=new User();
            user.setUserID(userID);
            UserAllergies userAllergies=new UserAllergies();
            userAllergies.setUser(user);
            userAllergies.setIngredientName(item2.getIngredient_name());
            userAllergiesRepository.save(userAllergies);
        }
        return ResponseEntity.ok(new MessageResponse("Update allergies successfully!!!"));
    }
    //tinh nutrion can thiet cho c?? th??? d???a theo (height,age,weight,workout_type,gender)
    @GetMapping("/calculate/nutrition")
    public NutritionDTO calculateNutrition(@RequestParam("height")int height,@RequestParam("weight")float weight,@RequestParam("type_workout")int typeWorkout,@RequestParam("age")int age,@RequestParam("gender")String gender){
        double caloNeed=0;
        double protein=0;
        double fat=0;
        double carb=0;
        float r=0;
        if(typeWorkout==1){
            r=Float.parseFloat("1.2");
        }
        else if(typeWorkout==2){
            r=Float.parseFloat("1.375");
        }
        else if(typeWorkout==3){
            r=Float.parseFloat("1.55");
        }
        else if(typeWorkout==4){
            r=Float.parseFloat("1.725");
        }
        if(gender.equals("male")){
            caloNeed=((weight*13.997)+(4.779*height)-(5.677*age)+88.362)*r;
            protein=(caloNeed*0.2)/4;
            fat=(caloNeed*0.25)/9;
            carb=(caloNeed*0.6)/4;
        }
        else if(gender.equals("female")){
            caloNeed=((weight*9.247)+(3.098*height)-(4.330*age)+447.593)*r;
            protein=(caloNeed*0.2)/4;
            fat=(caloNeed*0.25)/9;
            carb=(caloNeed*0.6)/4;
        }
        NutritionDTO dto=new NutritionDTO();
        dto.setCalories((float)caloNeed);
        dto.setCarb((float)carb);
        dto.setProtein((float)protein);
        dto.setFat((float)fat);
        return  dto;
    }
    //chuc nang view all user
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/viewall")
    public ListUserResponse viewAllUser(@RequestParam("page")int page,@RequestParam("limit")int limit){
        ListUserResponse listUserResponse=new ListUserResponse();
        listUserResponse.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit, Sort.by("firstName"));
        listUserResponse.setListUser(userService.findAll(pageable));
        listUserResponse.setTotalPage((int)Math.ceil((double)userService.totalItem()/limit));
        return listUserResponse;
    }
    //chuc nang change status cua user
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/changestatus/{id}")
    public ResponseEntity<?>changeStatus(@PathVariable("id")int id,@RequestBody UserDTO dto){
        User newUser=userRepository.findByUserID(id);
        if(newUser!=null){
            newUser.setIs_active(dto.isIs_active());
            userService.save(newUser);
            return ResponseEntity.ok(new MessageResponse("Change status successully!!!"));
        }
        else{
           return ResponseEntity.badRequest().body(new MessageResponse("Not found user id "+id));
        }
    }
    @GetMapping("/getpreferences/{id}")
    public ListIngredientCriteria getPreference(@PathVariable("id")int userID){
        List<UserPreference>entity=userPreferencesRepository.findByUser_UserID(userID);
        List<IngredientCriteria>result=new ArrayList<>();
        for(UserPreference item:entity){
            IngredientCriteria ingredientCriteria=new IngredientCriteria();
            ingredientCriteria.setIngredient_name(item.getIngredientName());
            result.add(ingredientCriteria);
        }
        ListIngredientCriteria listIngredientCriteria=new ListIngredientCriteria();
        listIngredientCriteria.setListIngredient(result);
        return listIngredientCriteria;
    }
    @GetMapping("/getallergies/{id}")
    public ListIngredientCriteria getAllergies(@PathVariable("id")int userID){
        List<UserAllergies>entity=userAllergiesRepository.findByUser_UserID(userID);
        List<IngredientCriteria>result=new ArrayList<>();
        for(UserAllergies item:entity){
            IngredientCriteria ingredientCriteria=new IngredientCriteria();
            ingredientCriteria.setIngredient_name(item.getIngredientName());
            result.add(ingredientCriteria);
        }
        ListIngredientCriteria listIngredientCriteria=new ListIngredientCriteria();
        listIngredientCriteria.setListIngredient(result);
        return listIngredientCriteria;
    }
}
