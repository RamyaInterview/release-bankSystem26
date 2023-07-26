package com.sample.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.app.dto.PostRequest;
import com.sample.app.service.JsonPlaceholderClient;
import com.sample.app.service.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	 private JsonPlaceholderClient jsonPlaceholderClient;
	 private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

	@Autowired
	    public PostController(JsonPlaceholderClient jsonPlaceholderClient) {
	        this.jsonPlaceholderClient = jsonPlaceholderClient;
	    }

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) throws JsonProcessingException {
		LOGGER.info("I am running create post service for " +postRequest.getUserId());
		return jsonPlaceholderClient.createPost(postRequest);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<String> getPost(@PathVariable int postId) {
		LOGGER.info("I am getting post details for document"+postId);
    	LOGGER.debug("I am at get post");
		return jsonPlaceholderClient.getPost(postId);
	}

}
