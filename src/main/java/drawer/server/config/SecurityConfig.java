package drawer.server.config;

import drawer.server.common.security.jwt.JwtAuthenticationFilter;
import drawer.server.common.security.jwt.JwtTokenManager;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${application.source.url}")
    private String url;

    private final JwtTokenManager jwtTokenManager;

    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf((auth) -> auth.disable());
        httpSecurity.formLogin((auth) -> auth.disable());
        httpSecurity.httpBasic((auth) -> auth.disable());

        httpSecurity.authorizeHttpRequests(
                (auth) ->
                        auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/login", "/api/v1/signup")
                                .permitAll()
                                .anyRequest()
                                .authenticated());

        httpSecurity.sessionManagement(
                (auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(
                new JwtAuthenticationFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.exceptionHandling((auth) -> auth.accessDeniedHandler(accessDeniedHandler));
        httpSecurity.exceptionHandling(
                (auth) -> auth.authenticationEntryPoint(authenticationEntryPoint));

        httpSecurity.cors(
                (corsCustomizer ->
                        corsCustomizer.configurationSource(
                                new CorsConfigurationSource() {
                                    @Override
                                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                        System.out.println("url : " + url);

                                        CorsConfiguration configuration = new CorsConfiguration();

                                        configuration.setAllowedOrigins(Collections.singletonList(url));
                                        configuration.setAllowedMethods(Collections.singletonList("*"));
                                        configuration.setAllowCredentials(true);
                                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                                        configuration.setMaxAge(3600L);

                                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                                        return configuration;
                                    }
                                })));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
}
