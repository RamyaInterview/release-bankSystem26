package com.sample.app.controller;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.dto.PostDocument;
import com.sample.app.entity.Document;
import com.sample.app.service.DocumentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ApiOperation(value = "Upload a document")
    @PostMapping("/{userId}")
	public Document uploadDocument(
            @ApiParam(value = "User ID") @PathVariable Long userId,
            @ApiParam(value = "Document file") @RequestParam("file") MultipartFile file
    ) {
        return documentService.uploadDocument(userId, file);
    }

    @ApiOperation(value = "Delete a document")
    @DeleteMapping("/{documentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocument(
            @ApiParam(value = "Document ID") @PathVariable Long documentId
    ) {
        documentService.deleteDocument(documentId);
    }

    @ApiOperation(value = "Get all user documents")
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Document> getUserDocuments(
            @ApiParam(value = "User ID") @PathVariable Long userId
    ) {
        return documentService.getUserDocuments(userId);
    }

	
       
}
