package com.advodraft.controller;

import com.advodraft.model.Document;
import com.advodraft.model.User;
import com.advodraft.repository.DocumentRepository;
import com.advodraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    // DTO - what the client sends when saving a document
    static class CreateDocumentRequest {
        public Long userId;
        public String title;
        public String type;
        public String partyA;
        public String partyB;
        public String content;
    }

    // POST /api/documents — save a new document
    @PostMapping
    public ResponseEntity<Document> saveDocument(@RequestBody CreateDocumentRequest request) {
        Optional<User> userOpt = userRepository.findById(request.userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Document document = new Document();
        document.setTitle(request.title);
        document.setType(request.type);
        document.setPartyA(request.partyA);
        document.setPartyB(request.partyB);
        document.setContent(request.content);
        document.setUser(userOpt.get());

        Document saved = documentRepository.save(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/documents/user/{userId} — get all documents for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Document>> getUserDocuments(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Document> documents = documentRepository.findByUserId(userId);
        return ResponseEntity.ok(documents);
    }

    // GET /api/documents/{id} — get one document by ID
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        Optional<Document> document = documentRepository.findById(id);
        return document.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/documents/{id} — delete a document
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        if (!documentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        documentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/documents/user/{userId}/count — count documents for a user
    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Long> getUserDocumentCount(@PathVariable Long userId) {
        long count = documentRepository.countByUserId(userId);
        return ResponseEntity.ok(count);
    }
}