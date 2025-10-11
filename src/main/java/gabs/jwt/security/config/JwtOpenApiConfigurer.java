package gabs.jwt.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * OpenAPI Configurer for JWT Security Library
 * This class contains the actual OpenAPI configuration and will only be compiled
 * when OpenAPI dependencies are available on the classpath
 */
@Configuration
@ConditionalOnClass(OpenAPI.class)
public class JwtOpenApiConfigurer {

    @Value("${spring.application.name:JWT Secured Application}")
    private String applicationName;

    @Value("${jwt.security.openapi.version:1.0}")
    private String apiVersion;

    @Value("${jwt.security.openapi.description:JWT-secured Spring Boot application}")
    private String apiDescription;

    @Bean
    public OpenAPI jwtOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " API")
                        .version(apiVersion)
                        .description(apiDescription + " with JWT authentication")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter your JWT token (without 'Bearer ' prefix)")));
    }
}