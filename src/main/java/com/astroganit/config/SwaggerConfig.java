package com.astroganit.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
   public static final String AUTHORIZATION_HEADER = "Authorization";
   public static final String AUTHORIZATION_NAME = "JWT";
   public static final String PASSAS = "header";

   private ApiKey apiKeys() {
      return new ApiKey("JWT", "Authorization", "header");
   }

   private List<SecurityContext> securityContext() {
      return Arrays.asList(SecurityContext.builder().securityReferences(this.securityReference()).build());
   }

   private List<SecurityReference> securityReference() {
      AuthorizationScope scopes = new AuthorizationScope("globle", "access everythings");
      return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{scopes}));
   }

   @Bean
   public Docket api() {
      return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.getInfo()).securityContexts(this.securityContext()).securitySchemes(Arrays.asList(this.apiKeys())).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
   }

   private ApiInfo getInfo() {
      return new ApiInfo("AstroGanit APIs", "This project is devoloped for create Apis for astroganit app.", "1.0", "Terms of Service", new Contact("Jitendra", "astroganit", "jitendrachauhan4099@gmail.com"), "License of Apis", "Api License URL", Collections.emptyList());
   }
}
