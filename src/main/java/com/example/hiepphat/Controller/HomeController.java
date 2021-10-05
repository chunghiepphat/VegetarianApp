package com.example.hiepphat.Controller;


import com.example.hiepphat.response.SearchResponse;
import com.example.hiepphat.service.BlogServiceImpl;
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
// chức năng advance search ( nều type=all thì get hết recipe,blog,video, type=recipe : get recipe,type=blog : get blog,type=video: getvideo)
    @GetMapping("/find")
    public SearchResponse searchRecipe(@RequestParam("search") String search,@RequestParam("type")String type) {
        SearchResponse result=new SearchResponse();
        if(type.equals("all")){
            result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search,search,search));
            result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search,search,search));
        }
        else if(type.equals("recipe")){
            result.setListRecipe(recipeService.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search,search,search));
        }
        else if(type.equals("blog")){
            result.setListBlog(blogService.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(search,search,search));
        }
        return result;
    }
}
