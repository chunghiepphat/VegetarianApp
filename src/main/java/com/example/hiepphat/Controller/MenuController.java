package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.Menu;
import com.example.hiepphat.Entity.Recipe;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.dtos.MenuDTO;
import com.example.hiepphat.repositories.MenuRespository;
import com.example.hiepphat.repositories.RecipeRepository;
import com.example.hiepphat.repositories.UserRepository;
import com.example.hiepphat.response.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuController {
@Autowired
    MenuRespository menuRespository;
@Autowired
    UserRepository userRepository;
@Autowired
    RecipeRepository recipeRepository;
    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/generate")
    public MenuResponse generateMenu(@RequestParam("id")int userID) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String spf=simpleDateFormat.format(date);
        Menu menu=new Menu();
        User user=new User();
        user.setUserID(userID);
        menu.setUser(user);
        menu.setTime(simpleDateFormat.parse(spf));
        User existUser=userRepository.findByUserID(userID);
        double caloNeed=0;
        if(existUser!=null){
            float r=0;
            if(existUser.getTypeWorkout()==1){
                r=Float.parseFloat("1.2");
            }
            else if(existUser.getTypeWorkout()==2){
                r=Float.parseFloat("1.375");
            }
            else if(existUser.getTypeWorkout()==3){
                r=Float.parseFloat("1.55");
            }
            else if(existUser.getTypeWorkout()==4){
                r=Float.parseFloat("1.725");
            }
            int currentDay=date.getYear()+1900;
            int yearUser=existUser.getBirth_date().getYear()+1900;
            int age=currentDay-yearUser;
            if(existUser.getGender().trim().equals("male")){
                caloNeed=((existUser.getWeight()*13.997)+(4.779*existUser.getHeight())-(5.677*age)+88.362)*r;
            }
            else if(existUser.getGender().trim().equals("female")){
                caloNeed=((existUser.getWeight()*9.247)+(3.098*existUser.getHeight())-(4.330*age)+447.593)*r;
            }
        }
        List<Recipe>listRecipe=recipeRepository.findAll();
        ArrayList<List<Recipe>>listParent=new ArrayList<>();
        List<Recipe>recipeList=new ArrayList<>();
        for(int i=0;i< listRecipe.size()-2;i++){
            for(int j=i+1;j<listRecipe.size()-1;j++){
                for(int k=j+1;k<listRecipe.size();k++){
                    if(listRecipe.get(i).getTotalCalo()+listRecipe.get(j).getTotalCalo()+listRecipe.get(k).getTotalCalo()>=caloNeed-20&&listRecipe.get(i).getTotalCalo()+listRecipe.get(j).getTotalCalo()+listRecipe.get(k).getTotalCalo()<=caloNeed){
                        List<Recipe>listRe=new ArrayList<>();
                        listRe.add(listRecipe.get(i));
                        listRe.add(listRecipe.get(j));
                        listRe.add(listRecipe.get(k));
                        Collections.sort(listRe);
                        listParent.add(listRe);;
                    }
                }
            }
        }
        List<MenuDTO>result=new ArrayList<>();
        Random rand=new Random();
        String dayofWeek[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        for(int l=0;l<dayofWeek.length;l++){
            int randomIndex=rand.nextInt(listParent.size());
            List<Recipe> randomArray=listParent.get(randomIndex);
            String mealofDay[]={"Breakfast","Lunch","Dinner"};
            for(int b=0;b<mealofDay.length;b++){
                MenuDTO dto=new MenuDTO();
                dto.setCalo(randomArray.get(b).getTotalCalo());
                dto.setRecipe_id(randomArray.get(b).getRecipeID());
                dto.setRecipe_thumbnail(randomArray.get(b).getRecipe_thumbnail());
                dto.setMeal_of_day(mealofDay[b]);
                dto.setDay_of_week(dayofWeek[l]);
                result.add(dto);
            }
        }
      MenuResponse menuResponse=new MenuResponse();
        menuResponse.setListRecipe(result);
        return menuResponse;
    }

}
