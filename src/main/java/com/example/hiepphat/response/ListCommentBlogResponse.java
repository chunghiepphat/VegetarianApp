package com.example.hiepphat.response;

import com.example.hiepphat.dtos.CommentBlogDTO;

import java.util.ArrayList;
import java.util.List;

public class ListCommentBlogResponse {
    private List<CommentBlogDTO>listCommentBlog=new ArrayList<>();

    public List<CommentBlogDTO> getListCommentBlog() {
        return listCommentBlog;
    }

    public void setListCommentBlog(List<CommentBlogDTO> listCommentBlog) {
        this.listCommentBlog = listCommentBlog;
    }
}
