package com.advodraft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String partyA;

    @Column(nullable = false)
    private String partyB;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ── Getters ──
    public Long getId()                 { return id; }
    public String getTitle()            { return title; }
    public String getType()             { return type; }
    public String getPartyA()           { return partyA; }
    public String getPartyB()           { return partyB; }
    public String getContent()          { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser()               { return user; }

    // ── Setters ──
    public void setId(Long id)             { this.id = id; }
    public void setTitle(String title)     { this.title = title; }
    public void setType(String type)       { this.type = type; }
    public void setPartyA(String partyA)   { this.partyA = partyA; }
    public void setPartyB(String partyB)   { this.partyB = partyB; }
    public void setContent(String content) { this.content = content; }
    public void setUser(User user)         { this.user = user; }
}