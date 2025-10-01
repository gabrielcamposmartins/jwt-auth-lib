package gabs.jwt.security.model;

/**
 * Interface that represents a user for JWT authentication
 * Implement this interface in your user entity to work with the JWT library
 */
public interface JwtUser {
    /**
     * Get the unique identifier for the user
     * @return user ID
     */
    Long getId();

    /**
     * Get the username for authentication
     * @return username
     */
    String getUsername();

    /**
     * Get the encoded password
     * @return encoded password
     */
    String getPassword();

    /**
     * Check if the user account is active
     * @return true if active, false otherwise
     */
    Boolean getActive();
}
