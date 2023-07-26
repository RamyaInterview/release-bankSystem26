package com.sample.app.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import com.sample.app.service.JsonPlaceholderClient;
import  com.sample.app.dto.PostRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreatePostIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private JsonPlaceholderClient jsonPlaceholderClient; // Inject the mocked Feign client

    @Test
    public void testCreatePost() {
        // Prepare the request body
        PostRequest postRequest = new PostRequest("Test Title", "Test Body", 1);

        // Prepare the mocked response from the JSONPlaceholder API
        when(jsonPlaceholderClient.createPost(any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Test Title"));

        // Prepare the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with body and headers
        HttpEntity<PostRequest> requestEntity = new HttpEntity<>(postRequest, headers);

        // Send the POST request to your Spring Boot application
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + 8080 + "/posts",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Verify the response status code and content
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Title", response.getBody());
    }
}
