package com.sample.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.app.dto.CommentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommentService {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public CommentService(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }
    @CircuitBreaker(name = "createComment", fallbackMethod = "createCommentFallback")

    public ResponseEntity<String> createComment(int postId, CommentRequest commentRequest) {
        return jsonPlaceholderClient.createComment(postId, commentRequest);
    }
}
