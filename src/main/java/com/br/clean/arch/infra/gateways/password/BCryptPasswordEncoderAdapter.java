package com.br.clean.arch.infra.gateways.password;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.clean.arch.application.gateways.password.RepositoryPasswordEncoder;

@Component
public class BCryptPasswordEncoderAdapter implements RepositoryPasswordEncoder{

	private final PasswordEncoder passwordEncoder;
	
	public BCryptPasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
