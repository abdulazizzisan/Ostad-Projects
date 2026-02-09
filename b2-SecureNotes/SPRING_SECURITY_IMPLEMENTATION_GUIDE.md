# Spring Security Implementation Guide

A complete step-by-step guide to implementing Spring Boot Security with authentication, authorization, and role-based access control.

---

## Table of Contents

1. [Project Setup & Dependencies](#1-project-setup--dependencies)
2. [Database Configuration](#2-database-configuration)
3. [Create User Entity](#3-create-user-entity)
4. [Create User Repository](#4-create-user-repository)
5. [Create DTOs](#5-create-dtos)
6. [Create Custom UserDetailsService](#6-create-custom-userdetailsservice)
7. [Configure Spring Security](#7-configure-spring-security)
8. [Create Authentication Controller](#8-create-authentication-controller)
9. [Create Protected Endpoints](#9-create-protected-endpoints)
10. [Testing the Application](#10-testing-the-application)

---

## 1. Project Setup & Dependencies

### 1.1 Create a new Spring Boot project

Use Spring Initializr or your IDE with the following dependencies:
- Spring Boot 3.5.9+
- Java 21
- Gradle or Maven

### 1.2 Add dependencies to `build.gradle`

```gradle
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.5.9'
	id 'io.spring.dependency-management' version '1.1.7'
}

group = 'dev.zisan'
version = '0.0.1-SNAPSHOT'

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

**Key Dependencies Explained:**
- `spring-boot-starter-security`: Core Spring Security functionality
- `spring-boot-starter-data-jpa`: JPA for database operations
- `spring-boot-starter-web`: REST API support
- `h2database`: In-memory database for development
- `lombok`: Reduces boilerplate code

---

## 2. Database Configuration

### 2.1 Configure `application.properties`

```properties
spring.application.name=b2-SecureNotes

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:securenotesdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## 3. Create User Entity

Create the `User` entity to store user credentials and roles.

**File:** `src/main/java/dev/zisan/secure_notes/entity/User.java`

```java
package dev.zisan.secure_notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
}
```

**Key Points:**
- `@ElementCollection`: Stores roles in a separate table (`user_roles`)
- `FetchType.EAGER`: Loads roles immediately with the user
- `password`: Will store BCrypt encrypted password
- `roles`: Set of role names (e.g., "USER", "ADMIN")

---

## 4. Create User Repository

**File:** `src/main/java/dev/zisan/secure_notes/repository/UserRepository.java`

```java
package dev.zisan.secure_notes.repository;

import dev.zisan.secure_notes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
```

---

## 5. Create DTOs

### 5.1 RegisterRequest DTO

**File:** `src/main/java/dev/zisan/secure_notes/dto/RegisterRequest.java`

```java
package dev.zisan.secure_notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
}
```

### 5.2 LoginRequest DTO

**File:** `src/main/java/dev/zisan/secure_notes/dto/LoginRequest.java`

```java
package dev.zisan.secure_notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
```

### 5.3 AuthResponse DTO

**File:** `src/main/java/dev/zisan/secure_notes/dto/AuthResponse.java`

```java
package dev.zisan.secure_notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String message;
    private String username;
}
```

---

## 6. Create Custom UserDetailsService

This service loads user-specific data and integrates with Spring Security.

**File:** `src/main/java/dev/zisan/secure_notes/service/CustomUserDetailsService.java`

```java
package dev.zisan.secure_notes.service;

import dev.zisan.secure_notes.entity.User;
import dev.zisan.secure_notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList()));
    }
}
```

**Key Points:**
- Implements `UserDetailsService` interface
- Prefixes each role with `ROLE_` (Spring Security convention)
- Returns Spring Security's built-in `User` object
- Throws `UsernameNotFoundException` if user doesn't exist

---

## 7. Configure Spring Security

### 7.1 Security Configuration

**File:** `src/main/java/dev/zisan/secure_notes/SecurityConfig.java`

```java
package dev.zisan.secure_notes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/users/**").hasRole("USER")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
```

**Configuration Breakdown:**

| Annotation | Purpose |
|------------|---------|
| `@Configuration` | Marks this as a Spring configuration class |
| `@EnableWebSecurity` | Enables Spring Security's web security support |
| `@EnableMethodSecurity` | Enables `@PreAuthorize` and `@PostAuthorize` annotations |

| Security Setting | Purpose |
|-----------------|---------|
| `csrf().disable()` | Disables CSRF protection (for stateless APIs) |
| `frameOptions().disable()` | Allows H2 console to load in frames |
| `permitAll()` for `/api/auth/**` | Allows public access to login/register |
| `permitAll()` for `/h2-console/**` | Allows access to H2 database console |
| `hasRole("USER")` | Only users with USER role can access |
| `hasRole("ADMIN")` | Only users with ADMIN role can access |
| `anyRequest().authenticated()` | All other endpoints require authentication |
| `SessionCreationPolicy.STATELESS` | No HTTP session, each request is independent |
| `httpBasic()` | Enables HTTP Basic authentication |

---

## 8. Create Authentication Controller

**File:** `src/main/java/dev/zisan/secure_notes/controller/AuthController.java`

```java
package dev.zisan.secure_notes.controller;

import dev.zisan.secure_notes.dto.AuthResponse;
import dev.zisan.secure_notes.dto.LoginRequest;
import dev.zisan.secure_notes.dto.RegisterRequest;
import dev.zisan.secure_notes.entity.User;
import dev.zisan.secure_notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(AuthResponse.builder()
                            .message("Username already exists")
                            .build());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("USER"))
                .build();

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse.builder()
                        .message("User registered successfully")
                        .username(user.getUsername())
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));

            return ResponseEntity.ok(AuthResponse.builder()
                    .message("Login successful")
                    .username(authentication.getName())
                    .build());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResponse.builder()
                            .message("Invalid username or password")
                            .build());
        }
    }
}
```

**Key Points:**
- **Register**: Creates new users with default `USER` role
- **Login**: Uses `AuthenticationManager` to validate credentials
- Passwords are encrypted using `BCryptPasswordEncoder`
- Returns appropriate HTTP status codes (201, 200, 401)

---

## 9. Create Protected Endpoints

### 9.1 Admin-Only Endpoint with @PreAuthorize

**File:** `src/main/java/dev/zisan/secure_notes/controller/NoteController.java`

```java
package dev.zisan.secure_notes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> ping(Authentication auth) {
        return ResponseEntity.ok(((User)auth.getPrincipal()).getAuthorities());
    }
}
```

**@PreAuthorize Expressions:**

| Expression | Description |
|------------|-------------|
| `hasRole('ADMIN')` | User must have ROLE_ADMIN |
| `hasAuthority('ROLE_ADMIN')` | Same as above (explicit) |
| `hasAnyRole('ADMIN', 'USER')` | User must have at least one of these roles |
| `isAuthenticated()` | User must be logged in |
| `permitAll()` | Allows everyone (even anonymous) |
| `denyAll()` | Denies everyone |

---

## 10. Testing the Application

### 10.1 Start the Application

```bash
./gradlew bootRun
```

### 10.2 Test Registration

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

**Expected Response:**
```json
{
  "message": "User registered successfully",
  "username": "admin"
}
```

### 10.3 Test Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

**Expected Response:**
```json
{
  "message": "Login successful",
  "username": "admin"
}
```

### 10.4 Test Protected Endpoint (with HTTP Basic Auth)

```bash
curl -X GET http://localhost:8080/api/notes \
  -u admin:admin123
```

**Expected Response:**
```json
[
  {
    "authority": "ROLE_USER"
  }
]
```

### 10.5 Create an Admin User

You can create an admin user directly in H2 Console:

1. Go to: http://localhost:8080/h2-console
2. JDBC URL: `jdbc:h2:mem:securenotesdb`
3. Run SQL:
```sql
INSERT INTO users (username, password) VALUES ('admin', '$2a$10$...');
INSERT INTO user_roles (user_id, role) VALUES (1, 'ADMIN');
```

Or modify the registration endpoint to accept roles:

```java
@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(Set.of("USER", "ADMIN"))  // Add ADMIN role
            .build();

    userRepository.save(user);
    // ...
}
```

---

## Security Best Practices

### 1. **Never Store Plain Text Passwords**
Always use `BCryptPasswordEncoder` or similar.

### 2. **Use HTTPS in Production**
HTTP Basic authentication sends credentials in base64 (easily decoded).

### 3. **Add JWT for Stateless Authentication**
For production, consider using JWT tokens instead of HTTP Basic.

### 4. **Implement Rate Limiting**
Prevent brute force attacks on login endpoints.

### 5. **Add Input Validation**
Use `@Valid` annotation with DTOs and validation annotations.

### 6. **Use Strong Passwords**
Enforce password complexity requirements.

### 7. **Log Security Events**
Track failed login attempts and suspicious activities.

### 8. **Keep Dependencies Updated**
Regularly update Spring Security and other dependencies.

---

## Troubleshooting

### Issue: @PreAuthorize not working

**Solution:** Add `@EnableMethodSecurity` to your SecurityConfig class.

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // <-- Add this
public class SecurityConfig { }
```

### Issue: 401 Unauthorized on valid credentials

**Check:**
1. Password is BCrypt encoded in database
2. Username exists in database
3. Roles are prefixed with `ROLE_`
4. User has the required role

### Issue: H2 Console 403

**Solution:** Add these rules to SecurityConfig:

```java
.requestMatchers("/h2-console/**").permitAll()
```

And disable frame options:

```java
.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
```

---

## Project Structure

```
src/main/java/dev/zisan/secure_notes/
├── controller/
│   ├── AuthController.java
│   └── NoteController.java
├── dto/
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── service/
│   └── CustomUserDetailsService.java
├── SecurityConfig.java
└── B2SecureNotesApplication.java
```

---

## Next Steps

1. **Add JWT Token Support** - Replace HTTP Basic with JWT
2. **Add Password Reset** - Implement forgot password functionality
3. **Add Email Verification** - Verify user email addresses
4. **Add Two-Factor Authentication** - Enhance security
5. **Add Role Hierarchy** - Configure role-based access levels
6. **Add Method-Level Security Tests** - Unit test security rules

---

## References

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Security Guide](https://spring.io/guides/gs/securing-web/)
- [BCrypt Password Encoder](https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html#pe-bcrypt)

---

**Last Updated:** January 2026
**Spring Boot Version:** 3.5.9
**Java Version:** 21