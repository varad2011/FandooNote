package com.jwtPractice.co;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
    private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days

    private String secret = "ThisIsASecret";

    private String tokenPrefix = "Bearer";

    private String headerString = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) {

        // We generate a token now.

        String JWT = Jwts.builder()

            .setSubject(username)

            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))

            .signWith(SignatureAlgorithm.HS512, secret)

            .compact();

        response.addHeader(headerString, tokenPrefix + " " + JWT);

    }

    public Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(headerString);

        if (token != null) {

            // parse the token.

            String username = Jwts.parser()

                .setSigningKey(secret)

                .parseClaimsJws(token)

                .getBody()

                .getSubject();

            if (username != null) // we managed to retrieve a user

            {

            	// wrong return value inserted not refer
                return null;

            }

        }

        return null;

    }
}
