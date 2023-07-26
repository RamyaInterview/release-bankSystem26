package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.app.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
}