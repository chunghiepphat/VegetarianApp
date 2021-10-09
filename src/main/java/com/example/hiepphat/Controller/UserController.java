package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.*;
import com.example.hiepphat.JWTUtils.JwtUtils;
import com.example.hiepphat.dtos.CommentBlogDTO;
import com.example.hiepphat.dtos.CommentRecipeDTO;
import com.example.hiepphat.dtos.UserDTO;
import com.example.hiepphat.repositories.CommentBlogRepository;
import com.example.hiepphat.repositories.CommentRecipeRepository;
import com.example.hiepphat.repositories.UserRepository;
import com.example.hiepphat.request.LoginRequest;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.request.UpdateUserRequest;
import com.example.hiepphat.response.JwtResponse;
import com.example.hiepphat.response.MessageResponse;
import com.example.hiepphat.response.SignupResponse;
import com.example.hiepphat.response.ViewLikedResponse;
import com.example.hiepphat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;

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
    //login
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
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
            return ResponseEntity.ok(new SignupResponse("Register Successfully",jwt));
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
                     return ResponseEntity.ok(new MessageResponse("Old password not correct!!!"));
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
    // chi tiết thông tin của 1 user dựa theo user id
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id")int id) {
        User user=userRepository.findByUserID(id);
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
        return dto;
    }
//chức năng comment trên blog
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
    // chức năng comment trên recipe
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
    //chức năng hiện tất cả những recipe,blog mà user id nào đó đã like dựa theo user_id
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/{id}/liked")
    public ViewLikedResponse viewLiked(@PathVariable("id") int id){
        ViewLikedResponse viewLikedResponse=new ViewLikedResponse();
        viewLikedResponse.setListRecipe(recipeService.findLikedRecipe(id));
        viewLikedResponse.setListBlog(blogService.findLikedBlog(id));
        return viewLikedResponse;
    }

    //chức năng delete comment của blog dựa theo comment id
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/deleteComment/{commentID}/blog")
    public ResponseEntity<?>deletecommentBlog(@PathVariable("commentID")int commentID){
         commentBlogService.deleteUserCommentByID(commentID);
        return ResponseEntity.ok(new MessageResponse("Delete successfully!!!"));
    }
    // chức năng delete comment của recipe dựa theo comment id
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/deleteComment/{commentID}/recipe")
    public ResponseEntity<?>deletecommentRecipe(@PathVariable("commentID")int commentID){
        commentRecipeRepository.deleteById(commentID);
        return ResponseEntity.ok(new MessageResponse("Delete successfully!!!"));
    }
    //chức năng edit comment blog
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
    //chức năng edit comment recipe
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
}
