package flab.emojeomo.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import static flab.emojeomo.global.util.JsonWebTokenGenerator.jwtKeyGenerator;


public class JsonWebTokenValidator {

    public boolean isValidToken(String token) throws Exception {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtKeyGenerator())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static String getSubject(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    private static Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtKeyGenerator())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
