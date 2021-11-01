package com.example.hiepphat.service;

import com.example.hiepphat.Entity.MenuRecipe;
import com.example.hiepphat.dtos.ListMenuDTO;
import com.example.hiepphat.dtos.MenuDTO;
import com.example.hiepphat.repositories.MenuRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;
@Service
public class MenuRecipeServiceImpl implements MenuRecipeService{
    @Autowired
    MenuRecipeRepository menuRecipeRepository;
    @Override
    public List<ListMenuDTO> findById(int id) {
        List<ListMenuDTO>result=new ArrayList<>();
        List<MenuRecipe>entity=menuRecipeRepository.findByMenu_User_UserID(id);
        ListMenuDTO dto=new ListMenuDTO();
        List<MenuDTO>listMenu=new ArrayList<>();
        List<MenuDTO>listMenu1=new ArrayList<>();
        List<MenuDTO>listMenu2=new ArrayList<>();
        List<MenuDTO>listMenu3=new ArrayList<>();
        List<MenuDTO>listMenu4=new ArrayList<>();
        List<MenuDTO>listMenu5=new ArrayList<>();
        List<Date>dateMenu=new ArrayList<>();
        for (MenuRecipe menuRecipe : entity) {
            if (!dateMenu.contains(menuRecipe.getDate())) {
                dateMenu.add(menuRecipe.getDate());
            }
        }
        dto.setDate(dateMenu.get(0));
        for(int i=0;i<3;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu.add(menu);
            dto.setListRecipe(listMenu);
        }
        result.add(dto);
        ListMenuDTO dto1=new ListMenuDTO();
        dto1.setDate(dateMenu.get(1));
        for(int i=3;i<6;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu1.add(menu);
            dto1.setListRecipe(listMenu1);
        }
        result.add(dto1);
        ListMenuDTO dto2=new ListMenuDTO();
        dto2.setDate(dateMenu.get(2));
        for(int i=6;i<9;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu2.add(menu);
            dto2.setListRecipe(listMenu2);
        }
        result.add(dto2);
        ListMenuDTO dto3=new ListMenuDTO();
        dto3.setDate(dateMenu.get(3));
        for(int i=9;i<12;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu3.add(menu);
            dto3.setListRecipe(listMenu3);
        }
        result.add(dto3);
        ListMenuDTO dto4=new ListMenuDTO();
        dto4.setDate(dateMenu.get(4));
        for(int i=12;i<15;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu4.add(menu);
            dto4.setListRecipe(listMenu4);
        }
        result.add(dto4);
        ListMenuDTO dto5=new ListMenuDTO();
        dto5.setDate(dateMenu.get(5));
        for(int i=15;i<18;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu5.add(menu);
            dto5.setListRecipe(listMenu5);
        }
        result.add(dto5);
        ListMenuDTO dto7=new ListMenuDTO();
        List<MenuDTO>listMenu7=new ArrayList<>();
        dto7.setDate(dateMenu.get(6));
        for(int i=18;i<21;i++){
            MenuDTO menu=new MenuDTO();
            menu.setRecipe_thumbnail(entity.get(i).getRecipe().getRecipe_thumbnail());
            menu.setRecipe_id(entity.get(i).getRecipe().getRecipeID());
            menu.setRecipe_title(entity.get(i).getRecipe().getRecipeTitle());
            menu.setCalo(entity.get(i).getRecipe().getTotalCalo());
            menu.setMeal_of_day(entity.get(i).getMealOfday());
            listMenu7.add(menu);
            dto7.setListRecipe(listMenu7);
        }
        result.add(dto7);
        return result;
        }

}
