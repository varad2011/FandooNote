package com.bridgelabz.FundooNote.Util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenGeneratorDecoder {
	
	private String Token = "varad";
	
	//Generate tokenId
	public String tokenGenerator(String id) {
		
		Algorithm algorithm = null;
		
		try {
			algorithm = Algorithm.HMAC256(Token);  
		}catch(IllegalArgumentException e){
		}
		String token = JWT.create().withClaim("id", id).sign(algorithm);
		return token;
	}
	
	//decode user id from token
	public String decodeToken(String token) {
		String userId;
		Verification verification = JWT.require(Algorithm.HMAC256(Token));
		JWTVerifier jwtVerifier = verification.build();
		DecodedJWT decodedJwt = jwtVerifier.verify(token);
		Claim claim = decodedJwt.getClaim("id");
		userId = claim.asString();
		return userId;
	}
}
