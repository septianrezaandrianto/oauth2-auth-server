package com.rnd.oauth2authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
//@EnableWebSecurity(debug =true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
                .authenticated().and().csrf().disable().cors().configurationSource( request -> corsConfiguration)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    this method for pass endpoint without security
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login/**");
    }
}
