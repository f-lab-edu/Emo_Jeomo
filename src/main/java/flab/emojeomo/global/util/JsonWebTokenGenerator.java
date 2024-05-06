package flab.emojeomo.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// Json web token & refresh token 생성하는 클래스
public class JsonWebTokenGenerator {
    @Value("${jwt.secret}")
    private static String salt;

    private static final long validTime = 1000L * 60 * 60;
    private static final long refreshTime = 1000L * 60 * 60 * 24 * 30;

    public static Key jwtKeyGenerator() {
        return Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public static String generateJsonWebToken(String userInfo) {
        Claims claims = Jwts.claims().setSubject(userInfo);
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date())
                   .signWith(jwtKeyGenerator(), SignatureAlgorithm.HS512)
                   .setExpiration(new Date(System.currentTimeMillis() + validTime))
                   .compact();
    }

    public static String generateRefreshToken() {
        Date now = new Date();
        Date refresh = new Date(now.getTime() + refreshTime);
        return Jwts.builder()
                   .setIssuedAt(new Date())
                   .setExpiration(refresh)
                   .compact();
    }
}
