package drawer.server.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPi() {
        Info info =
                new Info()
                        .version("v0.0.1")
                        .title("Drawer")
                        .description("API for managing bookmarks and RSS tags in a personal collection.");

        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components =
                new Components()
                        .addSecuritySchemes(
                                jwt,
                                new SecurityScheme()
                                        .name(jwt)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"));

        return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);
    }
}
