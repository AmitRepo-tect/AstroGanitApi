package com.astroganit.config;

import com.astroganit.security.CustomUserDetailService;
import com.astroganit.security.JwtAuthenticationEntryPoint;
import com.astroganit.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizedUrl;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(
   prePostEnabled = true
)
public class SecurityConfig {
   public static final String[] PUBLIC_URLS = new String[]{"/api/ganit/v1/auth/token", "/", "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**", "/**"};
   public static final String[] AUTH_URLS = new String[]{"/api/user/update/profile/**"};
   @Autowired
   private CustomUserDetailService customUserDetailService;
   @Autowired
   private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
   @Autowired
   private JwtAuthenticationFilter jwtAuthenticationFilter;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      ((HttpSecurity)((HttpSecurity)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((HttpSecurity)http.csrf().disable()).authorizeHttpRequests().requestMatchers(AUTH_URLS)).authenticated().requestMatchers(PUBLIC_URLS)).permitAll().anyRequest()).authenticated().and()).exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      http.authenticationProvider(this.daoAuthenticationProvider());
      return (SecurityFilterChain)http.build();
   }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(this.customUserDetailService);
      provider.setPasswordEncoder(this.passworEncoder());
      return provider;
   }

   @Bean
   public PasswordEncoder passworEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
   }
}
