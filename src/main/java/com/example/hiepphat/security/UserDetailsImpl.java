package com.example.hiepphat.security;


import com.example.hiepphat.Entity.Role;
import com.example.hiepphat.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String first_name;
	private String last_name;
	private String email;
	@JsonIgnore
	private String password;
	private String about_me;
	private String phone_number;
	private String profile_image;
	private String country;
	private String facebook_link;
	private String instagram_link;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int id, String first_name,String last_name, String email, String password,String about_me,String phone_number,String profile_image,String country,
			String facebook_link,String instagram_link,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.about_me=about_me;
		this.phone_number=phone_number;
		this.profile_image=profile_image;
		this.country=country;
		this.facebook_link=facebook_link;
		this.instagram_link=instagram_link;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRole_name()));
		return new UserDetailsImpl(
				user.getUserID(),
				user.getFirst_name(),
				user.getLast_name(),
				user.getEmail(),
				user.getPassword(),
				user.getAbout_me(),
				user.getPhone_number(),
				user.getProfile_image(),
				user.getCountry(),
				user.getFacebook_link(),
				user.getInstagram_link(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getAbout_me() {
		return about_me;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public String getCountry() {
		return country;
	}

	public String getFacebook_link() {
		return facebook_link;
	}

	public String getInstagram_link() {
		return instagram_link;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return first_name+""+last_name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}
}
