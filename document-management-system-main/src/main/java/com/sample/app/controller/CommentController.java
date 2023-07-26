package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.app.dto.Comment;
import com.sample.app.dto.CommentRequest;
import com.sample.app.service.CommentService;

@RestController
@RequestMapping("/posts")
public class CommentController {


	@Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

	
    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> createCommentForPost(@PathVariable int postId, @RequestBody CommentRequest commentRequest) {
        return commentService.createComment(postId, commentRequest);
    }

    
    

}


