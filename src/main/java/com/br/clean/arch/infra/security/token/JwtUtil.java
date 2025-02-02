package com.br.clean.arch.infra.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expiration;

	public String generateToken(String userId, String role) {
		return Jwts.builder()
				.setSubject(userId) // UUID como String
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUserId(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject(); // Retorna UUID como String
	}

	public String extractRole(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role", String.class);
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException
				| IllegalArgumentException e) {
			return false;
		}
	}

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSigningKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject(); // Retorna o 'subject' (userId) do token
	}

	
}
