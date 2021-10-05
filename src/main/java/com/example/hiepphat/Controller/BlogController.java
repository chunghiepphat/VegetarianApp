package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.*;
import com.example.hiepphat.dtos.BlogDTO;
import com.example.hiepphat.dtos.LikeBlogDTO;
import com.example.hiepphat.dtos.LikeRecipeDTO;
import com.example.hiepphat.dtos.RecipeDTO;
import com.example.hiepphat.repositories.LikeBlogRepository;
import com.example.hiepphat.request.BlogRequest;
import com.example.hiepphat.request.RecipeRequest;
import com.example.hiepphat.response.*;
import com.example.hiepphat.service.BlogServiceImpl;
import com.example.hiepphat.service.LikeBlogService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    LikeBlogService likeBlogService;
    @Autowired
    LikeBlogRepository likeBlogRepository;
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
            throw new Exception("Nout found blog id:"+ id);
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
    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@Valid @RequestBody BlogRequest blogRequest) throws ParseException {
        Blog blog=new Blog();
        User user=new User();
        user.setUserID(blogRequest.getUser_id());
        blog.setUser(user);
        blog.setBlog_content(blogRequest.getBlog_content());
        blog.setBlogTitle(blogRequest.getBlog_title());
        blog.setBlog_subtitle(blogRequest.getBlog_subtitle());
        blog.setBlog_thumbnail(blogRequest.getBlog_thumbnail());
        blog.setIs_active(true);
        java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
        blog.setTime(date);
        blogService.save(blog);
        return ResponseEntity.ok(new MessageResponse("Post blog successfully!!!"));
    }
    @GetMapping("/get5bestblog")
    public TenBlogResponse getbestblog() {
        TenBlogResponse result=new TenBlogResponse();
        result.setListResult(blogService.findBestBlog());
        return result;
    }

    @PreAuthorize("hasAuthority('user')")
    @PostMapping("like")
    public ResponseEntity<?>likeBlog(@RequestBody LikeBlogDTO dto){
        LikeBlog likeBlog=likeBlogService.findByUser_UserIDAndBlog_BlogID(dto.getUser_id(),dto.getBlog_id());
        if(likeBlog!=null){
            likeBlogRepository.delete(likeBlog);
            return ResponseEntity.ok(new MessageResponse("Unlike"));
        }
        else{
            LikeBlog newLike=new LikeBlog();
            User user=new User();
            user.setUserID(dto.getUser_id());
            Blog blog=new Blog();
            blog.setBlogID(dto.getBlog_id());
            newLike.setUser(user);
            newLike.setBlog(blog);
            likeBlogRepository.save(newLike);
            return ResponseEntity.ok(new MessageResponse("Liked"));
        }
    }
}
