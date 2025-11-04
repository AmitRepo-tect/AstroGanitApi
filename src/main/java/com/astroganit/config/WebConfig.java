package com.astroganit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")   // ✅ sab endpoints ke liye
                        .allowedOrigins("http://64.227.131.121") // ✅ frontend origin allow
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ methods
                        .allowedHeaders("*"); // ✅ headers allow
            }
        };
    }
}
