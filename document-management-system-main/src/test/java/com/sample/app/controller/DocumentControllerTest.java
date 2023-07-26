package com.sample.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.entity.Document;
import com.sample.app.service.DocumentService;

@RunWith(MockitoJUnitRunner.class)
public class DocumentControllerTest {
    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;

    private Long userId;
    private Long documentId;
    private MultipartFile file;
    private Document document;

    @Before
    public void setup() {
        userId = 1L;
        documentId = 1L;
        file = new MockMultipartFile("test.pdf", new byte[0]);

        document = new Document();
        document.setId(documentId);
        document.setFilename("test.pdf");
        document.setMimeType("application/pdf");
        document.setContent(new byte[0]);
    }

   
    @Test
    public void testUploadDocument() {
        when(documentService.uploadDocument(userId, file)).thenReturn(document);

        Document uploadedDocument = documentController.uploadDocument(userId, file);

        assertEquals(document.getId(), uploadedDocument.getId());
        assertEquals(document.getFilename(), uploadedDocument.getFilename());
        assertEquals(document.getMimeType(), uploadedDocument.getMimeType());
        assertEquals(document.getContent(), uploadedDocument.getContent());

        verify(documentService, times(1)).uploadDocument(userId, file);
    }

    @Test
    public void testDeleteDocument() {
        documentController.deleteDocument(documentId);

        verify(documentService, times(1)).deleteDocument(documentId);
    }

    @Test
    public void testGetUserDocuments() {
        List<Document> userDocuments = new ArrayList<>();
        userDocuments.add(document);

        when(documentService.getUserDocuments(userId)).thenReturn(userDocuments);

        List<Document> retrievedDocuments = documentController.getUserDocuments(userId);

        assertEquals(1, retrievedDocuments.size());
        assertEquals(document.getId(), retrievedDocuments.get(0).getId());
        assertEquals(document.getFilename(), retrievedDocuments.get(0).getFilename());
        assertEquals(document.getMimeType(), retrievedDocuments.get(0).getMimeType());
        assertEquals(document.getContent(), retrievedDocuments.get(0).getContent());

        verify(documentService, times(1)).getUserDocuments(userId);
    }
}
