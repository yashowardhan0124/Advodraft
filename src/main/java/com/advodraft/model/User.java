package com.advodraft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ═══════════════════════════════════════════════════════════════════
 * USER ENTITY - Your First Database Table!
 * ═══════════════════════════════════════════════════════════════════
 * 
 * WHAT IS AN ENTITY?
 * - Java class that maps to a database table
 * - Each instance = one row
 * - Each field = one column
 * 
 * THIS CLASS → "users" TABLE IN DATABASE
 * 
 * INTERVIEW:
 * "An entity is a JPA-annotated class that represents a database table.
 *  Hibernate automatically creates the table from this class."
 * 
 * ═══════════════════════════════════════════════════════════════════
 */

@Entity  // ← Tells JPA: "This is a database table!"
@Table(name = "users")  // ← Table name (optional, defaults to class name)
@Data  // ← Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // ← Lombok: generates User()
@AllArgsConstructor  // ← Lombok: generates User(id, email, fullName, createdAt)
@Builder  // ← Lombok: generates User.builder().email("...").build()
public class User {

    /**
     * ═══════════════════════════════════════════════════════════════
     * PRIMARY KEY
     * ═══════════════════════════════════════════════════════════════
     * 
     * @Id - Marks this as the primary key
     * @GeneratedValue - Auto-generate values
     * 
     * STRATEGY OPTIONS:
     * - IDENTITY: Database auto-increments (1, 2, 3, ...)
     * - AUTO: JPA picks best strategy
     * - SEQUENCE: Uses database sequence
     * - UUID: Generates UUIDs
     * 
     * WHY IDENTITY?
     * - Simple and works well with PostgreSQL
     * - Database handles ID generation
     * 
     * WHY Long NOT int?
     * - Long can be null (useful for new objects)
     * - Larger range (avoid running out of IDs)
     * - Industry standard
     * 
     * INTERVIEW:
     * "@Id marks primary key. @GeneratedValue(IDENTITY) means database
     *  auto-increments the ID. We use Long to allow null for new entities."
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ═══════════════════════════════════════════════════════════════
     * EMAIL - Unique identifier
     * ═══════════════════════════════════════════════════════════════
     * 
     * @Column - Configures the database column
     * 
     * PROPERTIES:
     * - unique = true: No duplicate emails (enforced by database!)
     * - nullable = false: Cannot be NULL (required field)
     * 
     * WHY unique?
     * - Each user needs unique email for login
     * - Database enforces this (better than app-level check)
     * 
     * INTERVIEW:
     * "@Column(unique=true) creates a UNIQUE constraint in database.
     *  nullable=false makes it a required field (NOT NULL constraint)."
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * ═══════════════════════════════════════════════════════════════
     * FULL NAME
     * ═══════════════════════════════════════════════════════════════
     */
    @Column(nullable = false)
    private String fullName;

    /**
     * ═══════════════════════════════════════════════════════════════
     * CREATED AT - Timestamp
     * ═══════════════════════════════════════════════════════════════
     * 
     * WHY LocalDateTime?
     * - Modern Java 8+ date/time API
     * - Better than old Date class
     * - Maps nicely to PostgreSQL TIMESTAMP
     * 
     * updatable = false: Once set, never change
     * (Creation time should never change!)
     * 
     * INTERVIEW:
     * "LocalDateTime is the modern Java API for dates. It's immutable,
     *  thread-safe, and works well with PostgreSQL TIMESTAMP columns."
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * ═══════════════════════════════════════════════════════════════
     * @PrePersist - Runs BEFORE saving to database
     * ═══════════════════════════════════════════════════════════════
     * 
     * WHAT: Lifecycle callback
     * WHEN: Just before entity is saved (INSERT)
     * WHY: Auto-set creation timestamp
     * 
     * HOW IT WORKS:
     * 1. You call: userRepository.save(user)
     * 2. JPA calls: this method
     * 3. Sets createdAt automatically
     * 4. Then saves to database
     * 
     * INTERVIEW:
     * "@PrePersist is a JPA lifecycle callback. It runs before INSERT,
     *  allowing us to set values automatically before saving."
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

/**
 * ═══════════════════════════════════════════════════════════════════
 * DATABASE TABLE (auto-created by Hibernate)
 * ═══════════════════════════════════════════════════════════════════
 * 
 * CREATE TABLE users (
 *     id         BIGSERIAL PRIMARY KEY,
 *     email      VARCHAR(255) UNIQUE NOT NULL,
 *     full_name  VARCHAR(255) NOT NULL,
 *     created_at TIMESTAMP NOT NULL
 * );
 * 
 * ═══════════════════════════════════════════════════════════════════
 * INTERVIEW QUESTIONS YOU CAN NOW ANSWER
 * ═══════════════════════════════════════════════════════════════════
 * 
 * Q: What is JPA?
 * A: "Java Persistence API - specification for ORM in Java.
 *     Maps Java objects to database tables. Hibernate is the implementation."
 * 
 * Q: What does @Entity do?
 * A: "Marks a class as a JPA entity, representing a database table.
 *     Hibernate creates the table from this class definition."
 * 
 * Q: Why use Lombok?
 * A: "Reduces boilerplate. @Data generates getters, setters, toString,
 *     equals, hashCode. @Builder adds builder pattern. Cleaner code."
 * 
 * Q: What's the difference between @Column and no annotation?
 * A: "@Column gives fine control: unique, nullable, length, name.
 *     Without it, JPA uses defaults: nullable=true, column name = field name."
 * 
 * Q: Why Long for ID instead of int?
 * A: "Long can be null (useful for new entities before saving).
 *     Larger range. Standard practice in JPA."
 * 
 * ═══════════════════════════════════════════════════════════════════
 */
