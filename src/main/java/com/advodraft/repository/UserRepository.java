package com.advodraft.repository;

import com.advodraft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ═══════════════════════════════════════════════════════════════════
 * USER REPOSITORY - Database Operations (NO CODE NEEDED!)
 * ═══════════════════════════════════════════════════════════════════
 * 
 * WHAT IS A REPOSITORY?
 * - Interface for database operations
 * - You declare methods, Spring implements them!
 * - No SQL needed (Spring generates it)
 * 
 * THE MAGIC:
 * You DON'T write implementation!
 * Spring Data JPA generates it automatically at runtime!
 * 
 * HOW?
 * extends JpaRepository<User, Long>
 *         ↓           ↓      ↓
 *    Interface    Entity  ID Type
 * 
 * INTERVIEW:
 * "Spring Data JPA auto-implements repository interfaces at runtime.
 *  By extending JpaRepository, we get 15+ methods for free without
 *  writing any implementation code."
 * 
 * ═══════════════════════════════════════════════════════════════════
 */

@Repository  // ← Tells Spring: "This is a data access component"
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * FREE METHODS (from JpaRepository - You get these automatically!)
     * ═══════════════════════════════════════════════════════════════
     * 
     * save(user)           → INSERT or UPDATE
     * findById(id)         → SELECT * FROM users WHERE id = ?
     * findAll()            → SELECT * FROM users
     * deleteById(id)       → DELETE FROM users WHERE id = ?
     * count()              → SELECT COUNT(*) FROM users
     * existsById(id)       → SELECT COUNT(*) > 0
     * 
     * AND MORE! Total: 15+ methods
     * 
     * EXAMPLE USAGE (in your controller/service):
     * 
     * // Save a user
     * User user = new User();
     * user.setEmail("test@example.com");
     * user.setFullName("Test User");
     * userRepository.save(user);  ← Spring generates INSERT SQL!
     * 
     * // Find by ID
     * Optional<User> user = userRepository.findById(1L);
     * 
     * // Get all users
     * List<User> users = userRepository.findAll();
     * 
     * // Delete
     * userRepository.deleteById(1L);
     * 
     * ═══════════════════════════════════════════════════════════════
     */

    /**
     * ═══════════════════════════════════════════════════════════════
     * CUSTOM QUERY METHOD - Spring Data JPA Magic!
     * ═══════════════════════════════════════════════════════════════
     * 
     * METHOD NAME → SQL QUERY
     * 
     * Spring Data JPA parses the method name and generates SQL!
     * 
     * Method name: findByEmail
     *              ↓
     * Breakdown:   find + By + Email
     *              ↓     ↓    ↓
     *           SELECT  WHERE email = ?
     * 
     * Generated SQL:
     * SELECT * FROM users WHERE email = ?
     * 
     * WHY Optional<User>?
     * - User might not exist
     * - Optional forces caller to handle absence
     * - Prevents NullPointerException
     * - Modern Java best practice
     * 
     * HOW TO USE:
     * Optional<User> user = userRepository.findByEmail("test@example.com");
     * if (user.isPresent()) {
     *     User found = user.get();
     *     // user exists!
     * } else {
     *     // user doesn't exist
     * }
     * 
     * INTERVIEW:
     * "Spring Data JPA parses method names to generate queries.
     *  'findByEmail' becomes 'SELECT * FROM users WHERE email = ?'.
     *  No SQL writing needed - just follow naming convention."
     * 
     * MORE EXAMPLES:
     * findByFullName(String name)     → WHERE full_name = ?
     * findByEmailAndFullName(...)     → WHERE email = ? AND full_name = ?
     * findByEmailContaining(String s) → WHERE email LIKE %?%
     * findByCreatedAtAfter(LocalDateTime date) → WHERE created_at > ?
     * 
     * ═══════════════════════════════════════════════════════════════
     */
    Optional<User> findByEmail(String email);

    /**
     * ═══════════════════════════════════════════════════════════════
     * ANOTHER CUSTOM METHOD - Check if email exists
     * ═══════════════════════════════════════════════════════════════
     * 
     * existsBy + Email → SELECT COUNT(*) > 0 FROM users WHERE email = ?
     * 
     * WHY USE THIS?
     * - More efficient than findByEmail().isPresent()
     * - Doesn't load the entire object
     * - Just checks existence
     * 
     * USAGE:
     * boolean exists = userRepository.existsByEmail("test@example.com");
     * if (exists) {
     *     // email is already taken!
     * }
     */
    boolean existsByEmail(String email);
}

/**
 * ═══════════════════════════════════════════════════════════════════
 * INTERVIEW QUESTIONS YOU CAN NOW ANSWER
 * ═══════════════════════════════════════════════════════════════════
 * 
 * Q: What is Spring Data JPA?
 * A: "Layer on top of JPA that auto-implements repository interfaces.
 *     Generates implementation at runtime based on method names.
 *     Eliminates need to write data access code."
 * 
 * Q: How does findByEmail work without implementation?
 * A: "Spring Data JPA parses the method name at runtime. 'findBy'
 *     indicates a query, 'Email' is the field name. It generates
 *     'SELECT * FROM users WHERE email = ?' automatically."
 * 
 * Q: What's the difference between JpaRepository and CrudRepository?
 * A: "JpaRepository extends CrudRepository and adds JPA-specific features
 *     like flush(), batch operations. Use JpaRepository for full features."
 * 
 * Q: Why use Optional instead of returning null?
 * A: "Optional makes absence explicit. Forces caller to handle both cases.
 *     Prevents NullPointerException. Shows intent clearly in API."
 * 
 * Q: Do you need to write SQL?
 * A: "For simple queries, no! Spring Data JPA generates SQL from method names.
 *     For complex queries, you can use @Query annotation with JPQL or native SQL."
 * 
 * ═══════════════════════════════════════════════════════════════════
 */
