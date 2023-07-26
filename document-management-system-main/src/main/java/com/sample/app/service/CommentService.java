package com.sample.app.service;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.app.dto.CommentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommentService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;



    public CommentService(JsonPlaceholderClient jsonPlaceholderClient, Resilience4JCircuitBreakerFactory circuitBreakerFactory) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @CircuitBreaker(name = "createComment", fallbackMethod = "createCommentFallback")

    public ResponseEntity<String> createComment(int postId, CommentRequest commentRequest) {
        return jsonPlaceholderClient.createComment(postId, commentRequest);
    }
    
    public ResponseEntity<String> createCommentFallback(int postId, CommentRequest commentRequest, Throwable throwable) {
        // Implement fallback logic here, return a default response or perform other actions.
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Comment creation is not available at the moment.");
    }
}
