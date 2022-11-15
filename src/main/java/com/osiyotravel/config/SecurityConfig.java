package com.osiyotravel.config;

import com.osiyotravel.config.detail.CustomProfileDetailsService;
import com.osiyotravel.config.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomProfileDetailsService customProfileDetailsService;

    private final JwtFilter jwtFilter;


    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };


    private static final String[] ALL_OPEN_API = {
            "/api/v1/auth/**",
            "/api/v1/category/get/**",
            "/api/v1/region/get/**",
            "/api/v1/district/get/**",
            "/init/**",
            "/api/v1/box/public/**",
            "/api/v1/attach/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(auth);
        auth.userDetailsService(customProfileDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers(ALL_OPEN_API).permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/employee-auth").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/branch/top-branches").permitAll()
                .anyRequest().authenticated();

        http.cors().and().csrf().disable();

    }
}
