package com.example.hiepphat.Controller;

import com.auth0.jwt.interfaces.Claim;
import com.example.hiepphat.Entity.Role;
import com.example.hiepphat.Entity.User;
import com.example.hiepphat.JWTUtils.JwtUtils;
import com.example.hiepphat.request.LoginRequest;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.response.JwtResponse;
import com.example.hiepphat.response.MessageResponse;
import com.example.hiepphat.security.UserDetailsImpl;
import com.example.hiepphat.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
 @Autowired
    UserServiceImpl userService;
@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    JwtUtils jwtUtils;

    private TokenStore tokenStore;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFirst_name(),
                userDetails.getLast_name(),
                userDetails.getEmail(),
                roles,
                userDetails.getAbout_me(),
                userDetails.getPhone_number(),
                userDetails.getProfile_image(),
                userDetails.getCountry(),
                userDetails.getFacebook_link(),
                userDetails.getInstagram_link()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
            Role roles=new Role();
            roles.setRole_id(1);
        // Create new user's account
        User user = new User(signUpRequest.getFirst_name(),signUpRequest.getLast_name(),
                signUpRequest.getEmail(),passwordEncoder.encode(signUpRequest.getPassword())
              ,true,roles);
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('admin')")
    public String adminAccess() {
        return "User.";
    }

   @GetMapping("/test")
    public String getClaim(String token){
        String user=jwtUtils.getClaimFromJwtToken(token);
        return user;
   }
    @GetMapping("/logout")
    public String logout(String token){
        tokenStore=new InMemoryTokenStore();
        OAuth2AccessToken oAuth2AccessToken=tokenStore.readAccessToken(token);
        if(oAuth2AccessToken!=null){
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
        return "OK";
    }

}
