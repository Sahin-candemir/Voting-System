package com.shnc.VotingSystem.security;

import org.springframework.stereotype.Component;

import com.shnc.VotingSystem.exceptions.APIException;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	//token üretimi
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate =new Date();  //şu anki zaman 
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())  //Set token veriliş tarihi
				.setExpiration(expireDate)          //Set son kullanım tarihi
				.signWith(SignatureAlgorithm.HS512,jwtSecret) //kullanılacak olan alg ve key
				.compact();
		return token;
	}
	//get username from the token
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	//validate jwt token
	public boolean validateToken(String token){
		try {
		     Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		     return true;
		}catch (SignatureException ex) {
			throw new APIException(HttpStatus.BAD_REQUEST, "invalid JWT signature");
		}catch (MalformedJwtException ex) {
			throw new APIException(HttpStatus.BAD_REQUEST, "invalid JWT token");
		}catch (ExpiredJwtException ex) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		}catch (UnsupportedJwtException ex) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		}catch (IllegalArgumentException ex) {
			throw new APIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
		
		
		
		
		
	}
}
