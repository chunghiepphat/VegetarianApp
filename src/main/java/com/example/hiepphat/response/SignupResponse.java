package com.example.hiepphat.response;

public class SignupResponse {
	private String access_token;

	public SignupResponse( String access_token) {
		this.access_token = access_token;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
