package com.advodraft.controller;

import com.advodraft.model.User;
import com.advodraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ═══════════════════════════════════════════════════════════════════
 * USER CONTROLLER - REST API for User Operations
 * ═══════════════════════════════════════════════════════════════════
 * 
 * CRUD OPERATIONS:
 * - C: Create (POST)
 * - R: Read (GET)
 * - U: Update (PUT)
 * - D: Delete (DELETE)
 * 
 * NEW CONCEPTS IN STEP 2:
 * - @Autowired (Dependency Injection)
 * - @PostMapping (Handle POST requests)
 * - @PathVariable (Get ID from URL)
 * - ResponseEntity (Control HTTP response)
 * 
 * ═══════════════════════════════════════════════════════════════════
 */

@RestController
@RequestMapping("/api/users")  // Base path for all endpoints
public class UserController {

    /**
     * ═══════════════════════════════════════════════════════════════
     * DEPENDENCY INJECTION - Spring's Magic!
     * ═══════════════════════════════════════════════════════════════
     * 
     * @Autowired - Tells Spring: "Inject this dependency for me"
     * 
     * WHAT HAPPENS:
     * 1. Spring sees @Autowired
     * 2. Finds UserRepository bean (created automatically)
     * 3. Injects it into this field
     * 4. You can use it!
     * 
     * WITHOUT SPRING (manual):
     * UserRepository userRepository = new UserRepositoryImpl();
     * 
     * WITH SPRING (automatic):
     * @Autowired
     * UserRepository userRepository;  ← Spring handles this!
     * 
     * WHY IS THIS GOOD?
     * - Loose coupling (easy to swap implementations)
     * - Testable (can inject mock repositories)
     * - No "new" keyword (Spring manages lifecycle)
     * 
     * INTERVIEW:
     * "Dependency Injection is a design pattern where Spring provides
     *  dependencies instead of objects creating them. @Autowired tells
     *  Spring to inject the dependency automatically."
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * ═══════════════════════════════════════════════════════════════
     * CREATE USER - POST /api/users
     * ═══════════════════════════════════════════════════════════════
     * 
     * @PostMapping - Handles HTTP POST requests
     * @RequestBody - Converts JSON to Java object
     * 
     * HOW IT WORKS:
     * 1. Client sends POST with JSON:
     *    {
     *      "email": "test@example.com",
     *      "fullName": "Test User"
     *    }
     * 
     * 2. Spring converts JSON → User object
     * 3. We save it to database
     * 4. Return saved user (with ID and createdAt filled in)
     * 
     * TEST WITH CURL:
     * curl -X POST http://localhost:8080/api/users \
     *   -H "Content-Type: application/json" \
     *   -d '{"email":"test@example.com","fullName":"Test User"}'
     * 
     * INTERVIEW:
     * "@RequestBody deserializes JSON into a Java object using Jackson.
     *  @PostMapping maps this method to HTTP POST requests."
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        // Save to database (ID and createdAt are auto-set)
        User savedUser = userRepository.save(user);
        
        // Return 201 Created with the saved user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * GET ALL USERS - GET /api/users
     * ═══════════════════════════════════════════════════════════════
     * 
     * Simple!
     * 1. Call findAll()
     * 2. Return list as JSON
     * 
     * TEST:
     * curl http://localhost:8080/api/users
     * 
     * OR open in browser:
     * http://localhost:8080/api/users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * GET USER BY ID - GET /api/users/{id}
     * ═══════════════════════════════════════════════════════════════
     * 
     * @PathVariable - Extracts value from URL
     * 
     * URL: /api/users/1
     *               ↓
     *      @PathVariable Long id = 1
     * 
     * Optional<User>:
     * - User might not exist
     * - Optional handles this gracefully
     * 
     * ResponseEntity:
     * - Control HTTP status code
     * - 200 OK if found
     * - 404 NOT FOUND if not found
     * 
     * TEST:
     * curl http://localhost:8080/api/users/1
     * 
     * INTERVIEW:
     * "@PathVariable extracts values from URL path. Optional represents
     *  a value that may or may not be present, preventing NPE."
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        
        // If user exists, return 200 OK with user
        // If not, return 404 NOT FOUND
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * UPDATE USER - PUT /api/users/{id}
     * ═══════════════════════════════════════════════════════════════
     * 
     * PUT = Update entire resource
     * 
     * PROCESS:
     * 1. Find existing user by ID
     * 2. Update fields
     * 3. Save (same save() method - JPA detects it's an update)
     * 4. Return updated user
     * 
     * TEST:
     * curl -X PUT http://localhost:8080/api/users/1 \
     *   -H "Content-Type: application/json" \
     *   -d '{"email":"updated@example.com","fullName":"Updated Name"}'
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {
        
        Optional<User> existingUser = userRepository.findById(id);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setEmail(updatedUser.getEmail());
            user.setFullName(updatedUser.getFullName());
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }
        
        return ResponseEntity.notFound().build();
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * DELETE USER - DELETE /api/users/{id}
     * ═══════════════════════════════════════════════════════════════
     * 
     * DELETE = Remove resource
     * 
     * TEST:
     * curl -X DELETE http://localhost:8080/api/users/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * FIND BY EMAIL - GET /api/users/email/{email}
     * ═══════════════════════════════════════════════════════════════
     * 
     * Uses our custom findByEmail method!
     * 
     * TEST:
     * curl http://localhost:8080/api/users/email/test@example.com
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
}

/**
 * ═══════════════════════════════════════════════════════════════════
 * HTTP STATUS CODES USED
 * ═══════════════════════════════════════════════════════════════════
 * 
 * 200 OK - Success (GET, PUT)
 * 201 CREATED - Resource created (POST)
 * 204 NO CONTENT - Success, no data to return (DELETE)
 * 404 NOT FOUND - Resource doesn't exist
 * 409 CONFLICT - Email already exists
 * 
 * ═══════════════════════════════════════════════════════════════════
 * INTERVIEW QUESTIONS
 * ═══════════════════════════════════════════════════════════════════
 * 
 * Q: What is Dependency Injection?
 * A: "Design pattern where dependencies are provided by framework
 *     instead of created by class. Spring injects dependencies via
 *     @Autowired. Enables loose coupling and easier testing."
 * 
 * Q: Difference between @PostMapping and @GetMapping?
 * A: "@GetMapping handles GET requests (retrieve data). @PostMapping
 *     handles POST requests (create data). Part of REST conventions."
 * 
 * Q: What does @RequestBody do?
 * A: "Deserializes HTTP request body (JSON) into Java object using
 *     Jackson library. Required for POST/PUT with JSON data."
 * 
 * Q: What is @PathVariable?
 * A: "Extracts value from URL path. For /users/{id}, @PathVariable
 *     Long id gets the ID value from URL."
 * 
 * Q: Why use ResponseEntity?
 * A: "Gives full control over HTTP response: status code, headers, body.
 *     Better than just returning object (can't control status code)."
 * 
 * ═══════════════════════════════════════════════════════════════════
 */
