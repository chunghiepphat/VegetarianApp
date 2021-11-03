package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;

import com.example.hiepphat.dtos.BlogDTO;

import com.example.hiepphat.dtos.TenBlogDTO;

import com.example.hiepphat.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{
@Autowired
private BlogRepository blogRepository;
    @Autowired
    private Converter converter;
    @Override
    public List<TenBlogDTO> findAll(Pageable pageable) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entites=blogRepository.findAll(pageable).getContent();
        for(Blog item:entites){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.getStatus()==2&&item.isPrivate()==false){
                results.add(blogDTO);
            }
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)blogRepository.count();
    }

    @Override
    public BlogDTO findblogbyID(int id) {
        Blog enties=blogRepository.findByBlogID(id);
            BlogDTO blogDTO= converter.toDTOBLOG(enties);
            return blogDTO;
    }

    @Override
    public List<TenBlogDTO> findTop10Records() {
        Date date=new Date(new java.util.Date().getTime());
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entities=blogRepository.find10blog(date);
        for (Blog item: entities){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                results.add(blogDTO);
            }
        }
        return results;
    }

    @Override
    public List<TenBlogDTO> findTop10ByUser_UserIDOrderByTimeDesc(int userID) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entities=blogRepository.findTop10ByUser_UserIDOrderByTimeDesc(userID);
        for (Blog item: entities){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
                results.add(blogDTO);
        }
        return results;
    }

    @Override
    public List<TenBlogDTO> findAllByUser_UserID(Pageable pageable, int userID) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entites=blogRepository.findAllByUser_UserID(pageable,userID);
        for(Blog item:entites){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
                results.add(blogDTO);
        }

        return results;
    }

    @Override
    public int countByUser_UserID(int userID) {
        return blogRepository.countByUser_UserID(userID);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<TenBlogDTO> findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String title,String fN,String ln) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entities=blogRepository.findByBlogTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+title+"%","%"+fN+"%","%"+ln+"%");
        for (Blog item: entities){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(item.getBlogID());
            if(item.getStatus()==2&&item.isPrivate()==false){
                results.add(blogDTO);
            }
        }
        return results;

    }

    @Override
    public List<TenBlogDTO> findBestBlog() {
        List<TenBlogDTO> result=new ArrayList<>();
        List<Blog>entities=blogRepository.findBestBlog();
        for(Blog item:entities){
            TenBlogDTO dto=converter.toDTO10BLOG(item);
            dto.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenBlogDTO> findLikedBlog(int id) {
        List<TenBlogDTO> result=new ArrayList<>();
        List<Blog>entities=blogRepository.findLikedBlog(id);
        for(Blog item:entities){
            TenBlogDTO dto=converter.toDTO10BLOG(item);
            dto.setTotalLike(item.getBlogID());
            if(item.getStatus()==2&&item.isPrivate()==false){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public int totalLike(int id) {
        return blogRepository.totalLike(id);
    }

    @Override
    public List<TenBlogDTO> findTop10ByUser_UserIDOrderByTimeDescOtherSide(int userID) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entities=blogRepository.findTop10ByUser_UserIDOrderByTimeDesc(userID);
        for (Blog item: entities){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                results.add(blogDTO);
            }
        }
        return results;
    }

    @Override
    public List<TenBlogDTO> findAllByUser_UserIDOtherSide(Pageable pageable, int userID) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entites=blogRepository.findAllByUser_UserID(pageable,userID);
        for(Blog item:entites){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.getStatus()==2&&item.isPrivate()==false){
                results.add(blogDTO);
            }
        }

        return results;
    }

    @Override
    public List<TenBlogDTO> findAllAdmin(Pageable pageable) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entites=blogRepository.findAll(pageable).getContent();
        for(Blog item:entites){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.isPrivate()==false){
                results.add(blogDTO);
            }
        }
        return results;
    }

    @Override
    public List<TenBlogDTO> showALLBlogUserbyAdmin(Pageable pageable, int userID) {
        List<TenBlogDTO> results=new ArrayList<>();
        List<Blog> entites=blogRepository.findAllByUser_UserID(pageable,userID);
        for(Blog item:entites){
            TenBlogDTO blogDTO= converter.toDTO10BLOG(item);
            blogDTO.setTotalLike(blogRepository.totalLike(item.getBlogID()));
            if(item.isPrivate()==false){
                results.add(blogDTO);
            }
        }

        return results;
    }


}
