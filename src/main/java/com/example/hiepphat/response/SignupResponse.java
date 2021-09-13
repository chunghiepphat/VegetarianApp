package com.example.hiepphat.response;

public class SignupResponse {
	private String message;
	private String access_token;

	public SignupResponse(String message, String access_token) {
		this.message = message;
		this.access_token = access_token;
	}

	public SignupResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
