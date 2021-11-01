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
    @Query(value = "SELECT TOP 10 * from Blogs where time_created<=? and status=2 and is_private='false' order by time_created desc",nativeQuery = true)
    List<Blog> find10blog(Date date);
    List<Blog> findTop10ByUser_UserIDOrderByTimeDesc(int userID);
    List<Blog> findAllByUser_UserID(Pageable pageable, int userID);
    int countByUser_UserID(int userID);
    List<Blog>findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fn,String ln);
    @Query(value = "Select * from Blogs where blog_id in (SELECT TOP 5 l.blog_id FROM Likes_Blog l,Blogs b where l.blog_id=b.blog_id and b.status=2 and b.is_private='false' GROUP BY l.blog_id ORDER BY COUNT (l.user_id) DESC)"
            ,nativeQuery = true)
    List<Blog>findBestBlog();
    @Query(value = "Select * from Blogs where blog_id in (select blog_id from Likes_Blog where user_id=? )"
            ,nativeQuery = true)
    List<Blog>findLikedBlog(int id);
    @Query(value = "SELECT COUNT(user_id) from Likes_Blog where blog_id=?",nativeQuery = true)
    int totalLike(int id);

}
