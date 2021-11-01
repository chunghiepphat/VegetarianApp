package com.example.hiepphat.response;



public class JwtResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponse(String token) {
		this.token = token;
	}
}
