package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.Menu;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.repositories.MenuRespository;
import com.example.hiepphat.repositories.UserRepository;
import com.example.hiepphat.response.TenRecipesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuController {
@Autowired
    MenuRespository menuRespository;
@Autowired
    UserRepository userRepository;
    @GetMapping("/generate")
    public int generateMenu(@RequestParam("id")int userID) throws ParseException {
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

        return (int)caloNeed;
    }

}
