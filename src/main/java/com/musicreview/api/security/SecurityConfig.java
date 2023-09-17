package com.musicreview.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                )
                .sessionManagement(
                        (sessionManagement) -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        (authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/reviews/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE,"/api/reviews/**").authenticated()
                                .requestMatchers(HttpMethod.PUT,"/api/reviews/**").authenticated()
                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

}
