package uz.pdp.facebookapp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.facebookapp.entity.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret.key}")
    private String secret;
    @Value("${jwt.token.secret.expiry}")
    private Long  expiry;
    public String generateToken(final User user){
        return Jwts.builder()
                .subject(user.getUsername())
                .id(String.valueOf(user.getId()))
                .claim("roles",user.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(key())
                .compact();
    }
    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public boolean isValid(String token) {
        Claims claims = parseAllClaims(token);
        Date date = extractExpiryDate(claims);
        return date.after(new Date());
    }

    public Claims parseAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private Date extractExpiryDate(Claims claims){
        return claims.getExpiration();
    }
}
