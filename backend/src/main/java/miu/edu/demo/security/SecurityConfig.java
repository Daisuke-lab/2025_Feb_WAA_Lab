package miu.edu.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
//    private final UserDetailsService userDetailsService;
//    private final JwtFilter jwtFilter;

    String[] roles = {"CLIENT","ADMIN"}; // You can make this a call from the DB
//  String [] roles = {"CLIENT"}; // Try this :)


//    @Bean
//    public UserDetailsService userDetailsSvc() {
//        return userDetailsService;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/**").permitAll()  // Permit all for authentication endpoints
//                        .requestMatchers("/api/v1/products").hasAuthority("CLIENT")  // Require "CLIENT" authority
                                .requestMatchers("/api/v1/products/**").hasAnyAuthority(roles)  // Dynamic authorities (replace 'roles' with your logic)
                                .anyRequest().authenticated()  // All other requests require authentication
                )
                // Configure session management (stateless)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
                // Add custom JWT filter before UsernamePasswordAuthenticationFilter
                //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }
//
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsSvc());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
}