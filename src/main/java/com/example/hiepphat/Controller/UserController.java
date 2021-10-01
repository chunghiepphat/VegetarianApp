package com.example.hiepphat.Controller;

import com.example.hiepphat.Entity.Role;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.JWTUtils.JwtUtils;
import com.example.hiepphat.dtos.UserDTO;
import com.example.hiepphat.repositories.UserRepository;
import com.example.hiepphat.request.LoginRequest;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.response.JwtResponse;
import com.example.hiepphat.response.SignupResponse;
import com.example.hiepphat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
 @Autowired
    UserServiceImpl userService;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    JwtUtils jwtUtils;
@Autowired
UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new SignupResponse("Error: Email is already taken!"));
        }
        else {
            Role roles = new Role();
            roles.setRole_id(1);
            // Create new user's account
            User user = new User(signUpRequest.getFirst_name(), signUpRequest.getLast_name(),
                    signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword())
                    , true, roles);
            user.setProvider("local");
            userService.save(user);
            String jwt=jwtUtils.generateJwtTokenSignup(signUpRequest);
            return ResponseEntity.ok(new SignupResponse("Register Successfully",jwt));
        }
    }
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('user')")
    public String adminAccess() {
        return "User.";
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('user')")
    public UserDTO update(@RequestBody UserDTO model,@PathVariable("id") int id){
             User oldUser=userService.findByUser_id(id);
             if(oldUser!=null) {
                 model.setId(oldUser.getUserID());
                 model.setEmail(oldUser.getEmail());
                 oldUser.setFirst_name(model.getFirst_name());
                 oldUser.setLast_name(model.getLast_name());
                 oldUser.setAbout_me(model.getAbout_me());
                 oldUser.setCountry(model.getCountry());
                 oldUser.setFacebook_link(model.getFacebook_link());
                 oldUser.setInstagram_link(model.getInstagram_link());
                 oldUser.setProfile_image(model.getProfile_image());
                 oldUser.setPhone_number(model.getPhone_number());
                 oldUser.setGender(model.getGender());
                 oldUser.setBirth_date(model.getBirth_date());
                 userService.save(oldUser);
             }
         return model;
    }
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id")int id) {
        User user=userRepository.findByUserID(id);
        UserDTO dto=new UserDTO();
        dto.setId(user.getUserID());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setEmail(user.getEmail());
        dto.setCountry(user.getCountry());
        dto.setBirth_date(user.getBirth_date());
        dto.setGender(user.getGender());
        dto.setAbout_me(user.getAbout_me());
        dto.setFacebook_link(user.getFacebook_link());
        dto.setInstagram_link(user.getInstagram_link());
        dto.setPhone_number(user.getPhone_number());
        dto.setProfile_image(user.getProfile_image());
        return dto;
    }
}
