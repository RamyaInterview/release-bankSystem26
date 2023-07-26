package com.sample.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sample.app.dto.CommentRequest;
import com.sample.app.dto.PostRequest;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @PostMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createPost(@RequestBody PostRequest postRequest);
    
    
    @GetMapping("/posts/{postId}")
    ResponseEntity<String> getPost(@PathVariable int i);
    
    @PostMapping("/posts/{postId}/comments")
    @Retryable(maxAttempts = 3) // Retry the operation up to 3 times if it fails
    ResponseEntity<String> createComment(@PathVariable("postId") int postId, @RequestBody CommentRequest commentRequest);

}
