package com.sample.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sample.app.service.JsonPlaceholderClient;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetPostIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private JsonPlaceholderClient jsonPlaceholderClient; // Inject the mocked Feign client

    @Test
    public void testGetPost() {
        // Prepare the mocked response from the JSONPlaceholder API
        when(jsonPlaceholderClient.getPost(1)).thenReturn(ResponseEntity.ok("Test Body"));

        // Send the GET request to your Spring Boot application
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + 8080 + "/posts/1",
                HttpMethod.GET,
                null,
                String.class
        );

        // Verify the response status code and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Body", response.getBody());
    }
}
