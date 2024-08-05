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
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
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

        http.oauth2Login(oauth2 -> {
            oauth2.loginPage("/oauth2/login/form")
                    .defaultSuccessUrl("/oauth2/login/success", true)
                    .failureUrl("/oauth2/login/failure")
                    .authorizationEndpoint(authorization ->
                            authorization.baseUri("/oauth2/authorization") //form login google
                                    .authorizationRequestRepository(getAuthorizationRequestRepository())
                    )
                    .tokenEndpoint(token ->
                            token.accessTokenResponseClient(getTokenResponseClient())
                    );
        });

        return http.build();
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> getAuthorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> getTokenResponseClient() {
        return new DefaultAuthorizationCodeTokenResponseClient();
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
