package com.martikan.restaurantapi.security;

import com.martikan.restaurantapi.security.filter.AuthenticationFilter;
import com.martikan.restaurantapi.security.filter.JwtAuthenticationFilter;
import com.martikan.restaurantapi.service.UserService;
import com.martikan.restaurantapi.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Security configuration.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                        }))
                .and()

                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/signIn").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/signUp").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(getAuthenticationFilter())

                .headers().frameOptions().disable();
    }

    @Bean
    public CorsFilter corsFilter() {

        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {

        final var authFilter = new AuthenticationFilter(jwtUtils);
        authFilter.setFilterProcessesUrl("/auth/signIn");
        authFilter.setAuthenticationManager(authenticationManager());

        return authFilter;
    }

    private JwtAuthenticationFilter getJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtils, userService);
    }

}
