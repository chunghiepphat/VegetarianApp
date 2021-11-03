package com.example.hiepphat.service;

import com.example.hiepphat.Entity.Blog;
import com.example.hiepphat.Entity.LikeRecipe;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.Entity.RecipeCategories;
import com.example.hiepphat.dtos.RecipeCategoriesDTO;
import com.example.hiepphat.dtos.RecipeDTO;

import com.example.hiepphat.dtos.TenRecipeDTO;
import com.example.hiepphat.repositories.LikeRecipeRepository;
import com.example.hiepphat.repositories.RecipeCategoriesRepository;
import com.example.hiepphat.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class RecipeServiceImpl implements RecipeService{
@Autowired
RecipeRepository recipeRepository;
    @Autowired
    private Converter converter;
    @Autowired
    LikeRecipeRepository likeRecipeRepository;
    @Autowired
    RecipeCategoriesRepository recipeCategoriesRepository;
    @Override
    public List<TenRecipeDTO> findAll(Pageable pageable) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAll(pageable).getContent();
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.getStatus()==2&&item.isPrivate()==false){
                results.add(recipeDTO);
            }
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int)recipeRepository.count();
    }

    @Override
    public List<TenRecipeDTO> findTop10Records() {
        Date date=new Date(new java.util.Date().getTime());
          List<TenRecipeDTO> results=new ArrayList<>();
            List<Recipe> entities=recipeRepository.find10recipes(date);
            for (Recipe item: entities){
                TenRecipeDTO recipeDTO= converter.toDTO10(item);
                recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
                if(item.getStatus()==2&&item.isPrivate()==false){
                    results.add(recipeDTO);
                }
            }
            return results;
    }

    @Override
    public RecipeDTO findrecipebyID(long id) {
        Recipe enties=recipeRepository.findByRecipeID(id);
            RecipeDTO recipeDTO= converter.toDTO(enties);
            return recipeDTO;
    }

    @Override
    public List<TenRecipeDTO> findTop10ByUserOrderByTimeDesc(int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entities=recipeRepository.findTop10ByUser_UserIDOrderByTimeDesc(userID);
        for (Recipe item: entities){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
                results.add(recipeDTO);
        }
        return results;
    }

    @Override
    public List<TenRecipeDTO> findAllByUser_UserID(Pageable pageable, int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAllByUser_UserID(pageable,userID);
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
                results.add(recipeDTO);
        }

        return results;
    }


    @Override
    public int countByUser_UserID(int userID) {
        return recipeRepository.countByUser_UserID(userID);
    }

    @Override
    public List<TenRecipeDTO> findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike(String search,String fn,String ln) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entities=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for (Recipe item: entities){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                results.add(recipeDTO);
            }
        }
        return results;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<RecipeCategoriesDTO> getAllRecipeCategory() {
        List<RecipeCategoriesDTO> result=new ArrayList<>();
        List<RecipeCategories> entities=recipeCategoriesRepository.getAll();
        for(RecipeCategories item:entities){
            RecipeCategoriesDTO recipeCategoriesDTO=new RecipeCategoriesDTO();
            recipeCategoriesDTO.setCategory_id(item.getRecipeCategoryID());
            recipeCategoriesDTO.setCategory_name(item.getRecipeCategoryName());
            recipeCategoriesDTO.setThumbnail(item.getRecipeCategoryThumbnail());
            result.add(recipeCategoriesDTO);
        }
        return result;
    }
    @Override
    public List<TenRecipeDTO> findLikedRecipe(int id) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entities=recipeRepository.findLikedRecipe(id);
        for(Recipe item:entities){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public void deleteByRecipeID(long id) {
         Recipe entity=recipeRepository.findByRecipeID(id);
         recipeRepository.delete(entity);
    }

    @Override
    public void deleteLike(long recipeID) {
        List<LikeRecipe>entities=likeRecipeRepository.findByRecipe_RecipeID(recipeID);
       for(LikeRecipe item:entities){
           likeRecipeRepository.deleteById(item.getId());
       }
    }

    @Override
    public int totalLike(long id) {
        return recipeRepository.totalLike(id);
    }

    @Override
    public List<TenRecipeDTO> findTop10ByUserOrderByTimeDescOtherside(int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entities=recipeRepository.findTop10ByUser_UserIDOrderByTimeDesc(userID);
        for (Recipe item: entities){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                results.add(recipeDTO);
            }
        }
        return results;
    }

    @Override
    public List<TenRecipeDTO> findAllByUser_UserIDOtherside(Pageable pageable, int userID) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAllByUser_UserID(pageable,userID);
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2){
                results.add(recipeDTO);
            }
        }

        return results;
    }

    @Override
    public List<TenRecipeDTO> findAllAdmin(Pageable pageable) {
        List<TenRecipeDTO> results=new ArrayList<>();
        List<Recipe> entites=recipeRepository.findAll(pageable).getContent();
        for(Recipe item:entites){
            TenRecipeDTO recipeDTO= converter.toDTO10(item);
            recipeDTO.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false){
                results.add(recipeDTO);
            }
        }
        return results;
    }

    @Override
    public List<TenRecipeDTO> filterCategory(String search,String fn,String ln,String category) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getRecipeCategories().getRecipeCategoryName().equalsIgnoreCase(category)){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterPreptime(String search,String fn,String ln,int prepTime) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getPrepTime()<=prepTime){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterDifficulty(String search,String fn,String ln,int diff) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getRecipeDifficulty()==diff){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterPrep_Cate(String search, String fn, String ln, int prepTime, String category) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getPrepTime()<=prepTime&&item.getRecipeCategories().getRecipeCategoryName().equalsIgnoreCase(category)){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterPrep_Diff(String search, String fn, String ln, int prepTime, int diff) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getPrepTime()<=prepTime&&item.getRecipeDifficulty()==diff){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterCate_Diff(String search, String fn, String ln, String category, int diff) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getRecipeDifficulty()==diff&&item.getRecipeCategories().getRecipeCategoryName().equalsIgnoreCase(category)){
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<TenRecipeDTO> filterALL(String search, String fn, String ln, String category, int diff, int prepTime) {
        List<TenRecipeDTO>result=new ArrayList<>();
        List<Recipe>entity=recipeRepository.findByRecipeTitleLikeOrUser_FirstNameLikeOrUser_LastNameLike("%"+search+"%","%"+fn+"%","%"+ln+"%");
        for(Recipe item:entity){
            TenRecipeDTO dto=converter.toDTO10(item);
            dto.setTotalLike(recipeRepository.totalLike(item.getRecipeID()));
            if(item.isPrivate()==false&&item.getStatus()==2&&item.getPrepTime()<=prepTime&&item.getRecipeCategories().getRecipeCategoryName().equalsIgnoreCase(category)&&item.getRecipeDifficulty()==diff){
                result.add(dto);
            }
        }
        return result;
    }


}
