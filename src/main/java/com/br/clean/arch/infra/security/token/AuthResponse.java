package com.br.clean.arch.infra.security.token;

public class AuthResponse {

	private final String token;
	
	public AuthResponse(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
