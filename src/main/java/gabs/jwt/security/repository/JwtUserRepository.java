package gabs.jwt.security.repository;

import gabs.jwt.security.model.JwtUser;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Repository interface for JWT user operations
 * Extend this interface in your user repository to get JWT-specific methods
 *
 * @param <T> The user entity type that implements JwtUser
 */
@NoRepositoryBean
public interface JwtUserRepository<T extends JwtUser> {

    /**
     * Find a user by username
     * @param username the username to search for
     * @return Optional containing the user if found
     */
    Optional<T> findByUsername(String username);

    /**
     * Check if a user exists by username
     * @param username the username to check
     * @return true if user exists, false otherwise
     */
    boolean existsByUsername(String username);
}
