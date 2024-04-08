package drawer.server.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenManager {

    private SecretKey secretKey;

    private final String ROLES = "roles";

    private final long TOKEN_VALID_MILLI_SEC = 1000L * 60 * 60;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    void initKey() {
        secretKey = Jwts.SIG.HS256.key().build();
    }

    public String createToken(String username, List<String> roles) {
        Date now = new Date();
        Date end = new Date(now.getTime() + TOKEN_VALID_MILLI_SEC);

        String token =
                Jwts.builder()
                        .subject(username)
                        .claim(ROLES, roles)
                        .issuedAt(now)
                        .expiration(end)
                        .signWith(secretKey)
                        .compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
