package com.example.hiepphat.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private List<String> roles;
	private String about_me;
	private String phone_number;
	private String profile_image;
	private String country;
	private String facebook_link;
	private String instagram_link;

	public JwtResponse(String token,int id, String first_name,String last_name, String email, List<String> roles, String about_me, String phone_number, String profile_image,
					   String country, String facebook_link, String instagram_link) {
		this.token=token;
		this.id = id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email = email;
		this.roles = roles;
		this.about_me = about_me;
		this.phone_number = phone_number;
		this.profile_image = profile_image;
		this.country = country;
		this.facebook_link = facebook_link;
		this.instagram_link = instagram_link;
	}

	public String getAbout_me() {
		return about_me;
	}

	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFacebook_link() {
		return facebook_link;
	}

	public void setFacebook_link(String facebook_link) {
		this.facebook_link = facebook_link;
	}

	public String getInstagram_link() {
		return instagram_link;
	}

	public void setInstagram_link(String instagram_link) {
		this.instagram_link = instagram_link;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public List<String> getRoles() {
		return roles;
	}
}
