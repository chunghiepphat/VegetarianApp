package com.example.hiepphat.service;

import com.example.hiepphat.Entity.LikeBlog;
import com.example.hiepphat.dtos.ListLikeDTO;
import com.example.hiepphat.repositories.LikeBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeBlogServiceImpl implements LikeBlogService {
    @Autowired
    private LikeBlogRepository likeBlogRepository;

    @Override
    public LikeBlog findByUser_UserIDAndBlog_BlogID(int user_id, int blog_id) {
        return likeBlogRepository.findByUser_UserIDAndBlog_BlogID(user_id,blog_id);
    }

    @Override
    public void deleteLike(int id) {
        List<LikeBlog>entities=likeBlogRepository.findByBlog_BlogID(id);
        likeBlogRepository.deleteAll(entities);
    }

    @Override
    public List<ListLikeDTO> viewListUserLike(int id) {
       List<ListLikeDTO>result=new ArrayList<>();
       List<LikeBlog>entity=likeBlogRepository.findByBlog_BlogID(id);
       for(LikeBlog item:entity){
           ListLikeDTO dto=new ListLikeDTO();
           dto.setUser_id(item.getUser().getUserID());
           result.add(dto);
       }
        return result;
    }
}
