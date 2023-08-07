package com.example.project101.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Record")
public class RecordTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "extractedText", columnDefinition = "TEXT")
    private String extractedText;

    @Column(name = "detectedLanguage", length = 20)
    private String detectedLanguage;

    @Column(name = "translatedText", columnDefinition = "TEXT")
    private String translatedText;

    @ManyToOne
    @JoinColumn(name = "memberEmail", referencedColumnName = "email", nullable = false)
    private MemberTable member;

    // Getters and Setters, Constructors, etc.
    // ...
}