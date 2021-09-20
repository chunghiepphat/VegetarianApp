package com.example.hiepphat.Controller;

import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.response.BlogResponse;
import com.example.hiepphat.response.RecipeResponse;
import com.example.hiepphat.response.TenBlogResponse;
import com.example.hiepphat.response.TenRecipesResponse;
import com.example.hiepphat.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    BlogServiceImpl blogService;
    @GetMapping("/getall")
    public BlogResponse showBlog(@RequestParam("page") int page, @RequestParam("limit") int limit){
        BlogResponse result2=new BlogResponse();
        result2.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result2.setListResult(blogService.findAll(pageable));
        result2.setTotalPage((int)Math.ceil((double)blogService.totalItem()/limit ));
        return result2;
    }
    @GetMapping("/getblogby/{id}")
    public BlogDTO showBlogbyID(@PathVariable long id) throws Exception {
        BlogDTO result=blogService.findblogbyID(id);
        if(result==null){
            throw new Exception("Nout found recipe id:"+ id);
        }
        return result;
    }
    @GetMapping("/get10blogs")
    public TenBlogResponse show10Blogs(){
        TenBlogResponse result=new TenBlogResponse();
        result.setListResult(blogService.findTop10Records());
        return result;
    }
    @GetMapping("/get10blogbyuser/{id}")
    public TenBlogResponse show10BlogsbyUserID(@PathVariable int id) throws Exception {
        TenBlogResponse result=new TenBlogResponse();
        result.setListResult(blogService.findTop10ByUser_UserIDOrderByTimeDesc(id));
        return result;
    }
    @GetMapping("/getallbyuserID/{id}")
    public BlogResponse showBlogByID(@RequestParam("page") int page, @RequestParam("limit") int limit,@PathVariable int id){
        BlogResponse result2=new BlogResponse();
        result2.setPage(page);
        Pageable pageable= PageRequest.of(page-1, limit,Sort.by("time").descending());
        result2.setListResult(blogService.findAllByUser_UserID(pageable,id));
        result2.setTotalPage((int)Math.ceil((double)blogService.countByUser_UserID(id)/limit ));
        return result2;
    }
}
