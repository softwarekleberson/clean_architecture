package com.br.clean.arch.application.gateways.password;

public interface RepositoryPasswordEncoder {
	
	String encode(String rawPassword);
	boolean matches(String rawPassword, String encodedPassword);
}
