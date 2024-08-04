package com.anhngo.mainproject.config;

import com.anhngo.mainproject.services.AccountServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountServiceDetails accountServiceDetails;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.disable());
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/login").authenticated();
            auth.requestMatchers("/admin").hasAnyRole("ADMIN", "STAFF");
            auth.requestMatchers("/users").hasRole("ADMIN");
            auth.requestMatchers("/products").hasAnyRole("ADMIN", "STAFF");
            auth.requestMatchers("/categories").hasAnyRole("ADMIN", "STAFF");
            auth.requestMatchers("/order/**").authenticated();
            auth.anyRequest().permitAll();
        });
        http.formLogin(form -> {
            form.loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login?error=true");
        });
        http.logout(logout -> {
            logout.logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID");
        });

        http.exceptionHandling(ex -> {
            ex.accessDeniedPage("/access-denied");
        });
        http.rememberMe(rememberMe -> {
//            1 ngày
            rememberMe.key("uniqueAndSecret").tokenValiditySeconds(86400).userDetailsService(accountServiceDetails);
        });
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(accountServiceDetails);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
