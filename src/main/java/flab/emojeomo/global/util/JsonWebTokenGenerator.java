package flab.emojeomo.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// Json web token & refresh token 생성하는 클래스
@Component
public class JsonWebTokenGenerator {
    @Value("${jwt.secret}")
    private String salt;

    public Key jwtKeyGenerator() {
        return Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJsonWebToken(Long userIndex) {
        Claims claims = Jwts.claims().setSubject(userIndex.toString());
        long validTime = 1000L * 60 * 60;
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date())
                   .signWith(jwtKeyGenerator(), SignatureAlgorithm.HS512)
                   .setExpiration(new Date(System.currentTimeMillis() + validTime))
                   .compact();
    }

    public String generateRefreshToken() {
        Date now = new Date();
        long refreshTime = 1000L * 60 * 60 * 24 * 30;
        Date refresh = new Date(now.getTime() + refreshTime);
        return Jwts.builder()
                   .setIssuedAt(new Date())
                   .setExpiration(refresh)
                   .compact();
    }
}
