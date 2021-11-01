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
    public SearchResponse searchRecipe(@RequestParam("search") String search,@RequestParam("type")String type,@RequestParam("userID")String userID) {
        SearchResponse result=new SearchResponse();
        switch (type) {
            case "all":
                if (!userID.trim().equals("")) {
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
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
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                    result.setListVideo(videoService.findByTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
                break;
            case "recipe":
                if (!userID.trim().equals("")) {
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
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
                    result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search, search, search));
                }
                break;
            case "blog":
                if (!userID.trim().equals("")) {
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
                if (!userID.trim().equals("")) {
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
