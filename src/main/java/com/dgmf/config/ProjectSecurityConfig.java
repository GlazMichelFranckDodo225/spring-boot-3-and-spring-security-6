package com.dgmf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Custom Security Configurations
        http.authorizeHttpRequests(
                    (requests) -> requests
                        .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards")
                            .authenticated()
                        .requestMatchers("/notices","/contact")
                            .permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // DEFINING AND MANAGING USERS
    /*
    InMemoryUserDetailsManager Class ==> Non-persistent implementation of UserDetailsManager which
    is backed by an in-memory map. Mainly intended for testing and demonstration purposes, where a
    full-blown persistent system isn't required.
    */
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        // Approach 1 : withDefaultPasswordEncoder() Method
        /*
        UserDetails Interface ==> Provides core user information. Implementations are not used directly
        by Spring Security for security purposes. They simply store user information which is later
        encapsulated into Authentication objects.
        */
        /*
        User Class ==> Implementation of UserDetails Interface. Models core user information retrieved
        by a UserDetailsService. Developers may use this class directly, subclass it, or write their
        own UserDetails implementation from scratch.
        */
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}
