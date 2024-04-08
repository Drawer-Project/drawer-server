package drawer.server.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class ResponseUtil {

    private ResponseUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static void doResponse(HttpServletResponse response
            , Object responseBody
            , HttpStatus status) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(responseBody);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status.value());
        response.getWriter().write(content);
    }
}
