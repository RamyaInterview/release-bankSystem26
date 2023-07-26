package com.sample.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sample.app.dto.PostRequest;

@Service
public class PostService {

	    private final JsonPlaceholderClient jsonPlaceholderClient;

	    public PostService(JsonPlaceholderClient jsonPlaceholderClient) {
	        this.jsonPlaceholderClient = jsonPlaceholderClient;
	    }

	    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
	        return jsonPlaceholderClient.createPost(postRequest);
	    }
	


	
}
