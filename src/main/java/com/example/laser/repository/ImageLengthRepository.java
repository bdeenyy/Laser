package com.example.laser.repository;

import com.example.laser.model.ImageLength;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageLengthRepository extends JpaRepository<ImageLength, Long> {
}

