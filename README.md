# JWT Security Library

A Spring Boot library that provides JWT authentication and authorization functionality for Spring Boot applications.

## Software Requirements

### Minimum Versions
- **Java**: 17 or higher
- **Spring Boot**: 3.2.0 or higher
- **Spring Security**: 6.2.0 or higher

### Dependencies
This library includes the following dependencies:
- Spring Boot Starter Security (3.2.0)
- Spring Boot Starter Web (3.2.0)
- JJWT API (0.11.5)
- JJWT Implementation (0.11.5)
- JJWT Jackson (0.11.5)
- SpringDoc OpenAPI Starter WebMVC UI (2.2.0)

## Installation

### Method 1: JitPack (Recommended)

Add this to your `build.gradle` file:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.gabrielcamposmartins:jwt-auth-lib:1.1'
}
```

### Method 2: Local JAR File

1. Download the JAR file from the [releases page](https://github.com/gabrielcamposmartins/jwt-auth-lib/releases)
2. Create a `libs` folder in your project root
3. Place the downloaded JAR in the libs folder
4. Add this to your `build.gradle`:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation files('libs/jwt-security-lib-1.0.0.jar')
    
    // Required transitive dependencies:
    implementation 'org.springframework.boot:spring-boot-starter-security:3.2.0'
    implementation 'org.springframework.boot:spring-boot-starter-web:3.2.0'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    implementation 'io.jsonwebtoken:jjwt-impl:0.11.5'
    implementation 'io.jsonwebtoken:jjwt-jackson:0.11.5'
}
```

### Method 3: GitHub Packages

```gradle
repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/gabrielcamposmartins/jwt-auth-lib")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation 'gabs:jwt-security-lib:1.0.0'
}
```

## Configuration

### Application Properties

Add these properties to your `application.yml` or `application.properties`:

```yaml
jwt:
  security:
    enabled: true  # Default: true
    secret: your-base64-encoded-secret-key  # Required: Base64 encoded secret key
    expiration: 86400000  # Required: Token expiration time in milliseconds (24 hours)
```

Or in `application.properties`:

```properties
jwt.security.enabled=true
jwt.security.secret=your-base64-encoded-secret-key
jwt.security.expiration=86400000
```

### Secret Key Generation

To generate a secure Base64 encoded secret key, you can use:

```java
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
```

## Usage

### Auto Configuration

The library provides auto-configuration that automatically sets up JWT security when added to your classpath. No additional configuration classes are needed.

### User Repository Implementation

Implement the `JwtUserRepository` interface in your application:

```java
@Repository
public class UserRepositoryImpl implements JwtUserRepository {
    
    @Override
    public JwtUser findByUsername(String username) {
        // Your implementation to find user by username
        // Return a JwtUser object or null if not found
    }
}
```

### JWT User Model

Your user entity should implement the `JwtUser` interface or extend it:

```java
@Entity
public class User implements JwtUser {
    
    @Id
    private String username;
    
    private String password;
    
    private String role;
    
    // Implement JwtUser methods
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getRole() {
        return role;
    }
    
    // Other getters and setters
}
```

### JWT Utility Usage

Inject and use the `JwtUtil` component in your services:

```java
@Service
public class AuthService {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public String login(String username, String password) {
        // Authenticate user (your logic here)
        if (authenticateUser(username, password)) {
            return jwtUtil.generateToken(username);
        }
        throw new AuthenticationException("Invalid credentials");
    }
    
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
    
    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}
```

### Security Configuration

The library automatically configures Spring Security with JWT authentication. The default configuration:

- Enables JWT authentication filter
- Configures password encoder (BCrypt)
- Sets up security filter chain
- Handles CORS and CSRF appropriately for JWT
- Configures CORS to allow all origins (`*`) with credentials support
- Provides OpenAPI/Swagger UI with JWT authentication support

### API Endpoints

Protected endpoints will require the `Authorization` header with the JWT token:

```
Authorization: Bearer <your-jwt-token>
```

## Features

- **Automatic Configuration**: Zero-config setup with Spring Boot auto-configuration
- **JWT Token Generation**: Generate secure JWT tokens for authenticated users
- **Token Validation**: Validate JWT tokens for incoming requests
- **User Authentication**: Integrate with your existing user management system
- **Security Filter**: Automatic request filtering and authentication
- **Password Encoding**: BCrypt password encoder included
- **Configurable Expiration**: Set custom token expiration times
- **Role-based Access**: Support for user roles and authorities
- **CORS Configuration**: Automatic CORS setup allowing all origins
- **OpenAPI Integration**: Automatic Swagger UI configuration with JWT authentication

## Disabling the Library

To disable the JWT security library, set the following property:

```yaml
jwt:
  security:
    enabled: false
```

## Version History

- **1.0.0**: Initial release with basic JWT authentication
- **1.1.0**: Enhanced configuration and JitPack support

## Contributing

Please feel free to submit issues and pull requests to improve this library.

## License

This project is licensed under the MIT License.
