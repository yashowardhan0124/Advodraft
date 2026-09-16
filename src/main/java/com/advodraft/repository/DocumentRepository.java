package com.advodraft.repository;

import com.advodraft.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ═══════════════════════════════════════════════════════════════════
 * DOCUMENT REPOSITORY - Database operations for Documents
 * ═══════════════════════════════════════════════════════════════════
 *
 * Just like UserRepository, this is an interface.
 * Spring Data JPA auto-implements it at runtime.
 *
 * JpaRepository<Document, Long>:
 *   Document = the entity this manages
 *   Long     = the type of Document's primary key
 *
 * FREE methods we get (same as UserRepository):
 *   save(doc)          → INSERT or UPDATE
 *   findById(id)       → SELECT WHERE id = ?
 *   findAll()          → SELECT all documents (all users — not what we want)
 *   deleteById(id)     → DELETE WHERE id = ?
 *   existsById(id)     → SELECT COUNT WHERE id = ?
 *
 * ═══════════════════════════════════════════════════════════════════
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    /**
     * ─────────────────────────────────────────────────────────────
     * Get all documents belonging to a specific user.
     *
     * Method name breakdown:
     *   findBy + User_Id → WHERE user_id = ?
     *
     * The underscore navigates the relationship:
     *   User  = the 'user' field on Document
     *   Id    = the 'id' field on User
     *
     * Generated SQL:
     *   SELECT * FROM documents WHERE user_id = ?
     *
     * This is the query we use most — "show me MY documents".
     * ─────────────────────────────────────────────────────────────
     */
    List<Document> findByUserId(Long userId);

    /**
     * ─────────────────────────────────────────────────────────────
     * Get all documents of a specific type for a specific user.
     * e.g. "Show me all NDAs for user with id=5"
     *
     * Method name: findBy + User_Id + And + Type
     * Generated SQL: SELECT * FROM documents WHERE user_id = ? AND type = ?
     *
     * Useful for filtering documents by type in the UI.
     * ─────────────────────────────────────────────────────────────
     */
    List<Document> findByUserIdAndType(Long userId, String type);

    /**
     * ─────────────────────────────────────────────────────────────
     * Count how many documents a user has.
     * Generated SQL: SELECT COUNT(*) FROM documents WHERE user_id = ?
     *
     * Useful for the dashboard stats card ("You have 7 documents").
     * ─────────────────────────────────────────────────────────────
     */
    long countByUserId(Long userId);
}
