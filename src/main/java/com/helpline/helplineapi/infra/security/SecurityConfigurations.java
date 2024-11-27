package com.helpline.helplineapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> {
                        System.out.println(authException);
                    });
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/docs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/report").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/nome").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/dashboard").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/h2-console").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/donations/report").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ws/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ws/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/chat").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/chat/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/chat/messages/**").authenticated()
                  .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
