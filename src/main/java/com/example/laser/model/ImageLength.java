package com.example.laser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ImageLength {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private double length;

    public ImageLength() {
    }

    public ImageLength(String fileName, double length) {
        this.fileName = fileName;
        this.length = length;
    }

    // getters and setters
}

