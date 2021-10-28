package com.example.hiepphat.repositories;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.LikeBlog;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.dtos.TenBlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
    public Blog findByBlogID(int id);
    List<Blog> findTop10ByTimeLessThanEqualOrderByTimeDesc(Date date);
    List<Blog> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<Blog> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<Blog>findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
    @Query(value = "Select blog_id,user_id,blog_title,blog_subtitle,blog_thumbnail,blog_content,is_active,time_created,time_updated,is_private,status from Blogs where blog_id in (select TOP 5 blog_id FROM Likes_Blog GROUP BY blog_id ORDER BY COUNT (user_id) DESC )"
            ,nativeQuery = true)
    List<Blog>findBestBlog();
    @Query(value = "Select blog_id,user_id,blog_title,blog_subtitle,blog_thumbnail,blog_content,is_active,time_created,time_updated,is_private,status from Blogs where blog_id in (select blog_id from Likes_Blog where user_id=? )"
            ,nativeQuery = true)
    List<Blog>findLikedBlog(int id);
    @Query(value = "SELECT COUNT(user_id) from Likes_Blog where blog_id=?",nativeQuery = true)
    int totalLike(int id);

}
