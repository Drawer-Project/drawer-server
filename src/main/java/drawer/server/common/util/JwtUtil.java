package drawer.server.common.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    private static final String USERNAME = "username";

    private static final String ROLE = "role";

    public static String createJwtToken(String username, String role, long expirationTime) {

        return Jwts.builder()
                .claim(USERNAME, username)
                .claim(ROLE, role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public static String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(USERNAME, String.class);
    }

    public static String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(ROLE, String.class);
    }

    public static Boolean isExpired(String token) {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}
