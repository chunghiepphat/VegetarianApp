package com.example.hiepphat.service;


import com.example.hiepphat.Entity.User;

import com.example.hiepphat.dtos.UserDTO;
import com.example.hiepphat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }



    @Override
    public User findByUser_id(int id) {
        return userRepository.findByUserID(id);
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO>result=new ArrayList<>();
        List<User>entity=userRepository.findAll(pageable).getContent();
        for(User item:entity){
            UserDTO dto=new UserDTO();
            dto.setFirst_name(item.getFirstName());
            dto.setLast_name(item.getLastName());
            dto.setProfile_image(item.getProfile_image());
            dto.setPhone_number(item.getPhone_number());
            dto.setRole(item.getRole().getRole_name());
            dto.setInstagram_link(item.getInstagram_link());
            dto.setFacebook_link(item.getFacebook_link());
            dto.setAbout_me(item.getAbout_me());
            dto.setCountry(item.getCountry());
            dto.setGender(item.getGender());
            dto.setBirth_date(item.getBirth_date());
            dto.setEmail(item.getEmail());
            dto.setId(item.getUserID());
            dto.setIs_active(item.isIs_active());
            result.add(dto);
        }
        return result;
    }

    @Override
    public int totalItem() {
        return (int)userRepository.count();
    }
}
