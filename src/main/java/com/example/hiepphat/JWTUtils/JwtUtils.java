package com.example.hiepphat.JWTUtils;



import com.example.hiepphat.Entity.User;
import com.example.hiepphat.repositories.UserRepository;
import com.example.hiepphat.request.SignUpRequest;
import com.example.hiepphat.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	@Autowired
	UserRepository userRepository;
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.claim("email",userPrincipal.getEmail())
				.claim("id",userPrincipal.getId()).claim("first_name",userPrincipal.getFirst_name())
				.claim("last_name",userPrincipal.getLast_name()).claim("roles",userPrincipal.getAuthorities())
				.claim("about_me",userPrincipal.getAbout_me()).claim("phone_number",userPrincipal.getPhone_number())
				.claim("profile_image",userPrincipal.getProfile_image()).claim("country",userPrincipal.getCountry())
				.claim("facebook_link",userPrincipal.getFacebook_link()).claim("instagram_link",userPrincipal.getInstagram_link())
				.claim("birth_date",userPrincipal.getBirth_date())
				.claim("gender",userPrincipal.getGender()).claim("is_active",userPrincipal.isIs_active())
				.claim("code_active",userPrincipal.getCode_active())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String generateJwtTokenSignup(SignUpRequest signUpRequest) {
		Optional<User> user=userRepository.findByEmail(signUpRequest.getEmail());
		return Jwts.builder()
				.claim("email",signUpRequest.getEmail()).claim("id",user.get().getUserID())
				.claim("first_name",signUpRequest.getFirst_name())
				.claim("last_name",signUpRequest.getLast_name()).claim("roles",1)
				.claim("about_me",null).claim("phone_number",null)
				.claim("profile_image",null).claim("country",null).claim("facebook_link",null)
				.claim("instagram_link",null).claim("birth_date",null).claim("gender",null).claim("is_active",1)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}


	public String getClaimFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("email",String.class);
	}
	public String getCodeActive(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("code_active",String.class);
	}
	public String getFirstName(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("first_name",String.class);
	}
	public String getLastName(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("last_name",String.class);
	}
	public boolean getStatusUser(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("is_active",Boolean.class);
	}
	public int getUserID(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("id",Integer.class);
	}
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
