package com.ghrnwjd.httpresponsecode.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghrnwjd.httpresponsecode.data.RoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf((c)-> c.disable())
            .cors(c -> c.disable())
            .authorizeHttpRequests((authorizeRequest) ->
                authorizeRequest
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/login").permitAll()// 401
                    .requestMatchers("/status/**").hasRole(String.valueOf(RoleType.ADMIN)) // 403
                    .anyRequest().authenticated()

            )
            .formLogin(login -> login
                    .defaultSuccessUrl("/")
                    .permitAll());
//            .exceptionHandling((e)->
//                    e.authenticationEntryPoint(unauthorizedEntryPoint).accessDeniedHandler(accessDeniedHandler)
//            );

        return http.build();
    }

    private final AuthenticationEntryPoint unauthorizedEntryPoint =
        (request, response, authException) -> {
            ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            String json = new ObjectMapper().writeValueAsString(fail);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        };

    private final AccessDeniedHandler accessDeniedHandler =
        (request, response, accessDeniedException) -> {
            ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden...");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            String json = new ObjectMapper().writeValueAsString(fail);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        };

    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;
    }
}
