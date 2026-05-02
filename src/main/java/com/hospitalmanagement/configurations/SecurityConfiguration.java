package com.hospitalmanagement.configurations;

import com.hospitalmanagement.oauthproviders.OAuthFailureHandler;
import com.hospitalmanagement.oauthproviders.OAuthSuccessHandler;
import com.hospitalmanagement.securityfilters.JWTAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
    private final JWTAuthFilter jwtAuthFilter ;
    private final OAuthFailureHandler oAuthFailureHandler ;
    private final OAuthSuccessHandler oAuthSuccessHandler ;
    private final PasswordEncoder bCryptPasswordEncoder ;

    public SecurityConfiguration(JWTAuthFilter jwtAuthFilter, OAuthFailureHandler oAuthFailureHandler, OAuthSuccessHandler oAuthSuccessHandler, PasswordEncoder bCryptPasswordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.oAuthFailureHandler = oAuthFailureHandler;
        this.oAuthSuccessHandler = oAuthSuccessHandler;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        return httpSecurity
                .csrf((csrfConfig) -> csrfConfig.disable())
                .sessionManagement((sessionConfig) -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (request) ->
                                request    .requestMatchers("/api/v1/auth/**").permitAll()

//                                        .requestMatchers("/api/v1/patients/**").hasAnyRole("PATIENT", "ADMIN")
//                                        .requestMatchers("/api/v1/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin((formConfig) -> formConfig.disable())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(OAuth->OAuth
                        .successHandler(oAuthSuccessHandler)
                        .failureHandler(oAuthFailureHandler))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("abhishek").password(getEncryptedPassword("Pass@123")).roles("ADMIN")
                .build();
        UserDetails user2 = User.builder().username("rutuja").password(getEncryptedPassword("Pass@123")).roles("DOCTOR")
                .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);

        return inMemoryUserDetailsManager;
    }

    private String getEncryptedPassword(String plainTest) {
        return bCryptPasswordEncoder.encode(plainTest);
    }
}
