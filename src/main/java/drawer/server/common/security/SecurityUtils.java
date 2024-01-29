package drawer.server.common.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class SecurityUtils {

    private static final String BLANK_OF_HEADER = " ";

    private static final int TOKEN_INDEX = 1;

    public static String resolveBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null) {
            return authHeader.split(BLANK_OF_HEADER)[TOKEN_INDEX];
        }

        return null;
    }
}
