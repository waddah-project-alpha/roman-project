package com.adobe.integertoroman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Web security config for our app. Note that this allows all access to our endpoints, and does not enforce any actual
 * security. However it's added as a proof of concept and to make it easier to actually implement security if required later
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/romannumeral")
                                                         .permitAll()
                                                         .requestMatchers("/actuator/*")
                                                         .permitAll()
                                                         .anyRequest()
                                                         .authenticated())
                                                         .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

}
