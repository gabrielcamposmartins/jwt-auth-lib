package gabs.jwt.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI Configuration for JWT Security Library
 * This configuration provides standardized JWT authentication setup for Swagger UI
 * Only active when OpenAPI classes are available on the classpath
 */
@Configuration
@ConditionalOnProperty(name = "jwt.security.enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(name = "io.swagger.v3.oas.models.OpenAPI")
public class JwtOpenApiConfig {
    // Configuration will be handled by JwtOpenApiConfigurer when OpenAPI is available
}