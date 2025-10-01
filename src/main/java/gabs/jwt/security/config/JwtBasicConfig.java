package gabs.jwt.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Basic security configuration that provides the PasswordEncoder bean
 * This is separated from the main security config to avoid circular dependencies
 */
@Configuration
@ConditionalOnProperty(name = "jwt.security.enabled", havingValue = "true", matchIfMissing = true)
public class JwtBasicConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
