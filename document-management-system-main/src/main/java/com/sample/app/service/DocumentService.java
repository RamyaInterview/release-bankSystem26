package com.sample.app.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.sample.app.dto.PostDocument;
import com.sample.app.entity.Document;
import com.sample.app.entity.User;
import com.sample.app.repository.DocumentRepository;
import com.sample.app.repository.UserRepository;

@Service
public class DocumentService {
	private final DocumentRepository documentRepository;
	private final UserRepository userRepository;

	@Autowired
	public DocumentService(DocumentRepository documentRepository, UserRepository userRepository) {
		this.documentRepository = documentRepository;
		this.userRepository = userRepository;
	}

	public Document uploadDocument(Long userId, MultipartFile file) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		String filename = file.getOriginalFilename();
		String mimeType = file.getContentType();
		byte[] content;

		try {
			content = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read the file: " + filename, e);
		}

		Document document = new Document(filename, mimeType, content, user);
		return documentRepository.save(document);
	}

	public void deleteDocument(Long documentId) {
		documentRepository.deleteById(documentId);
	}

	public List<Document> getUserDocuments(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		return user.getDocuments();
	}

	public MultiValueMap<String, String> createPost(Long documentId) throws Exception {
		HttpPost request = new HttpPost("https://jsonplaceholder.typicode.com/posts");
        // Set the method to POST.
      //  request.setMethod("POST");
        // Create a new instance of the JsonObject class.
        JsonObject jsonObject = new JsonObject();
        // Add the data you want to post to the JsonObject.
        jsonObject.addProperty("title", "My First Post");
        jsonObject.addProperty("body", "This is my first post on JsonPlaceholder.");
        jsonObject.addProperty("documentId", +documentId);
        // Set the body of the HttpRequest object to the JsonObject.
        request.setEntity(new StringEntity(jsonObject.toString()));
        // Send the request using the send() method of the HttpRequest object.
        HttpClientBuilder.create().build().execute(request);
	//	return request;
		return null;
	}
 

}
