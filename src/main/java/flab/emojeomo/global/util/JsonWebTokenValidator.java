package flab.emojeomo.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonWebTokenValidator {

    private final JsonWebTokenGenerator jsonWebTokenGenerator;

    public boolean isValidToken(String token) throws Exception {
        try {
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(jsonWebTokenGenerator.jwtKeyGenerator())
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getSubject(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jsonWebTokenGenerator.jwtKeyGenerator())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
