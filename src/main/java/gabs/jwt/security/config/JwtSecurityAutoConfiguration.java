package gabs.jwt.security.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Auto-configuration for JWT Security Library
 * This class automatically configures the JWT security components when the library is included
 */
@AutoConfiguration
@ConditionalOnProperty(name = "jwt.security.enabled", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = "gabs.jwt.security")
@Import({JwtBasicConfig.class, JwtSecurityConfig.class, JwtOpenApiConfigurer.class})
public class JwtSecurityAutoConfiguration {
    // Auto-configuration class - Spring Boot will automatically detect and configure
    // all components when this library is added to the classpath
}
