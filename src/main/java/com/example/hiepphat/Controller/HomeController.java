package com.example.hiepphat.Controller;


import com.example.hiepphat.Entity.User;
import com.example.hiepphat.Entity.UserBehavior;
import com.example.hiepphat.repositories.UserBehaviorRepository;
import com.example.hiepphat.response.SearchResponse;
import com.example.hiepphat.service.BlogServiceImpl;
import com.example.hiepphat.service.RecipeServiceImpl;
import com.example.hiepphat.service.VideoServiceImpl;
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
@Autowired
private VideoServiceImpl videoService;
@Autowired
private UserBehaviorRepository userBehaviorRepository;
// chức năng advance search ( nều type=all thì get hết recipe,blog,video, type=recipe : get recipe,type=blog : get blog,type=video: getvideo)
    @GetMapping("/find")
    public SearchResponse searchRecipe(@RequestParam("search") String search,@RequestParam(value = "type",required = false)String type,@RequestParam(value = "userID",required = false)String userID,
                                       @RequestParam(value = "difficulty",required = false)String diff,@RequestParam(value = "category",required = false)String category,
                                       @RequestParam(value = "prepare_time",required = false)String pre_time
                                       ) {
        SearchResponse result=new SearchResponse();
        if(type==null) {
            if (userID != null) {
                if(pre_time!=null&&diff!=null&&category!=null){
                    result.setListRecipe(recipeService.filterALL(search,search,search,category,Integer.parseInt(diff),Integer.parseInt(pre_time)));
                }
                else if(category!=null&&pre_time!=null){
                    result.setListRecipe(recipeService.filterPrep_Cate(search,search,search,Integer.parseInt(pre_time),category));
                }
                else if(category!=null&&diff!=null){
                    result.setListRecipe(recipeService.filterCate_Diff(search,search,search,category,Integer.parseInt(diff)));
                }
                else if(pre_time!=null&&diff!=null){
                    result.setListRecipe(recipeService.filterPrep_Diff(search,search,search,Integer.parseInt(pre_time),Integer.parseInt(diff)));
                }
                else if(category!=null){
                    result.setListRecipe(recipeService.filterCategory(search,search,search,category));
                }
                else if(pre_time!=null){
                    result.setListRecipe(recipeService.filterPreptime(search,search,search,Integer.parseInt(pre_time)));
                }
                else if(diff!=null){
                    result.setListRecipe(recipeService.filterDifficulty(search,search,search,Integer.parseInt(diff)));
                }
                else{
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListVideo(videoService.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
                UserBehavior userBehaviorOld = userBehaviorRepository.findByUser_UserIDAndQuerry(Integer.parseInt(userID), search);
                if (userBehaviorOld != null) {
                    userBehaviorOld.setFrequency(userBehaviorOld.getFrequency() + 1);
                    userBehaviorRepository.save(userBehaviorOld);
                } else {
                    UserBehavior userBehavior = new UserBehavior();
                    User user = new User();
                    user.setUserID(Integer.parseInt(userID));
                    userBehavior.setUser(user);
                    userBehavior.setQuerry(search);
                    userBehavior.setFrequency(1);
                    userBehaviorRepository.save(userBehavior);
                }
            } else {
                if(pre_time!=null&&diff!=null&&category!=null){
                    result.setListRecipe(recipeService.filterALL(search,search,search,category,Integer.parseInt(diff),Integer.parseInt(pre_time)));
                }
                else if(category!=null&&pre_time!=null){
                    result.setListRecipe(recipeService.filterPrep_Cate(search,search,search,Integer.parseInt(pre_time),category));
                }
                else if(category!=null&&diff!=null){
                    result.setListRecipe(recipeService.filterCate_Diff(search,search,search,category,Integer.parseInt(diff)));
                }
                else if(pre_time!=null&&diff!=null){
                    result.setListRecipe(recipeService.filterPrep_Diff(search,search,search,Integer.parseInt(pre_time),Integer.parseInt(diff)));
                }
                else if(category!=null){
                    result.setListRecipe(recipeService.filterCategory(search,search,search,category));
                }
                else if(pre_time!=null){
                    result.setListRecipe(recipeService.filterPreptime(search,search,search,Integer.parseInt(pre_time)));
                }
                else if(diff!=null){
                    result.setListRecipe(recipeService.filterDifficulty(search,search,search,Integer.parseInt(diff)));
                }
                else{
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListVideo(videoService.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
            }
        }
        else
        switch (type) {
            case "recipe":
                if (userID!=null) {
                    if(pre_time!=null&&diff!=null&&category!=null){
                        result.setListRecipe(recipeService.filterALL(search,search,search,category,Integer.parseInt(diff),Integer.parseInt(pre_time)));
                    }
                      else if(category!=null&&pre_time!=null){
                          result.setListRecipe(recipeService.filterPrep_Cate(search,search,search,Integer.parseInt(pre_time),category));
                      }
                      else if(category!=null&&diff!=null){
                          result.setListRecipe(recipeService.filterCate_Diff(search,search,search,category,Integer.parseInt(diff)));
                      }
                      else if(pre_time!=null&&diff!=null){
                          result.setListRecipe(recipeService.filterPrep_Diff(search,search,search,Integer.parseInt(pre_time),Integer.parseInt(diff)));
                      }
                   else if(category!=null){
                        result.setListRecipe(recipeService.filterCategory(search,search,search,category));
                    }
                    else if(pre_time!=null){
                        result.setListRecipe(recipeService.filterPreptime(search,search,search,Integer.parseInt(pre_time)));
                    }
                    else if(diff!=null){
                        result.setListRecipe(recipeService.filterDifficulty(search,search,search,Integer.parseInt(diff)));
                    }
                    else{
                        result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    }
                    UserBehavior userBehaviorOld = userBehaviorRepository.findByUser_UserIDAndQuerry(Integer.parseInt(userID), search);
                    if (userBehaviorOld != null) {
                        userBehaviorOld.setFrequency(userBehaviorOld.getFrequency() + 1);
                        userBehaviorRepository.save(userBehaviorOld);
                    } else {
                        UserBehavior userBehavior = new UserBehavior();
                        User user = new User();
                        user.setUserID(Integer.parseInt(userID));
                        userBehavior.setUser(user);
                        userBehavior.setQuerry(search);
                        userBehavior.setFrequency(1);
                        userBehaviorRepository.save(userBehavior);
                    }
                } else {
                    if(category!=null){
                        result.setListRecipe(recipeService.filterCategory(search,search,search,category));
                    }
                    else if(pre_time!=null){
                        result.setListRecipe(recipeService.filterPreptime(search,search,search,Integer.parseInt(pre_time)));
                    }
                    else if(diff!=null){
                        result.setListRecipe(recipeService.filterDifficulty(search,search,search,Integer.parseInt(diff)));
                    }
                    else{
                        result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    }
                }
                break;
            case "blog":
                if (userID!=null) {
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    UserBehavior userBehaviorOld = userBehaviorRepository.findByUser_UserIDAndQuerry(Integer.parseInt(userID), search);
                    if (userBehaviorOld != null) {
                        userBehaviorOld.setFrequency(userBehaviorOld.getFrequency() + 1);
                        userBehaviorRepository.save(userBehaviorOld);
                    } else {
                        UserBehavior userBehavior = new UserBehavior();
                        User user = new User();
                        user.setUserID(Integer.parseInt(userID));
                        userBehavior.setUser(user);
                        userBehavior.setQuerry(search);
                        userBehavior.setFrequency(1);
                        userBehaviorRepository.save(userBehavior);
                    }
                } else {
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
                break;
            case "video":
                if (userID!=null) {
                    result.setListVideo(videoService.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    UserBehavior userBehaviorOld = userBehaviorRepository.findByUser_UserIDAndQuerry(Integer.parseInt(userID), search);
                    if (userBehaviorOld != null) {
                        userBehaviorOld.setFrequency(userBehaviorOld.getFrequency() + 1);
                        userBehaviorRepository.save(userBehaviorOld);
                    } else {
                        UserBehavior userBehavior = new UserBehavior();
                        User user = new User();
                        user.setUserID(Integer.parseInt(userID));
                        userBehavior.setUser(user);
                        userBehavior.setQuerry(search);
                        userBehavior.setFrequency(1);
                        userBehaviorRepository.save(userBehavior);
                    }
                } else {
                    result.setListVideo(videoService.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
                break;
        }
        return result;
    }
}
