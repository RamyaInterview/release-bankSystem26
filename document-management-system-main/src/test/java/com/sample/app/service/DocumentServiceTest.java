package com.sample.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.entity.Document;
import com.sample.app.entity.User;
import com.sample.app.entity.UserTest;
import com.sample.app.repository.DocumentRepository;
import com.sample.app.repository.UserRepository;
import com.sample.app.service.DocumentService;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {
	@Mock
	private DocumentRepository documentRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private DocumentService documentService;

	private User user;
	private Document document;

	@Before
	public void setup() {
		// Create a sample user for testing
		user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document for testing
		document = new Document("test.pdf", "application/pdf", new byte[0], user);
		document.setId(1L);

		// Mock UserRepository
		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		when(userRepository.findById(2L)).thenReturn(Optional.empty());

		// Mock DocumentRepository
		when(documentRepository.save(any(Document.class))).thenReturn(document);
		doNothing().when(documentRepository).deleteById(document.getId());
	}

	@Test
	public void testUploadDocument() throws IOException {
		MultipartFile file = new MockMultipartFile("test.pdf", new byte[0]);
		Document uploadedDocument = documentService.uploadDocument(user.getId(), file);

		assertEquals(document.getId(), uploadedDocument.getId());
		assertEquals(user, uploadedDocument.getUser());
		assertEquals("test.pdf", uploadedDocument.getFilename());
		assertEquals("application/pdf", uploadedDocument.getMimeType());
		assertEquals(0, uploadedDocument.getContent().length);

		verify(userRepository, times(1)).findById(user.getId());
		verify(documentRepository, times(1)).save(any(Document.class));
	}

	@Test(expected = RuntimeException.class)
	public void testUploadDocument_UserNotFound() throws IOException {
		MultipartFile file = new MockMultipartFile("test.pdf", new byte[0]);
		documentService.uploadDocument(2L, file); // User with ID 2 does not exist
	}

	@Test(expected = RuntimeException.class)
	public void testUploadDocument_FailedToReadFile() throws IOException {
		MultipartFile file = mock(MultipartFile.class);
		when(file.getOriginalFilename()).thenReturn("test.pdf");
		when(file.getBytes()).thenThrow(new IOException());

		documentService.uploadDocument(user.getId(), file);
	}

	@Test
	public void testDeleteDocument() {
		documentService.deleteDocument(document.getId());

		verify(documentRepository, times(1)).deleteById(document.getId());
	}

	@Test
	public void testGetUserDocuments() {
		List<Document> documents = new ArrayList<>();
		documents.add(document);
		user.setDocuments(documents);

		List<Document> userDocuments = documentService.getUserDocuments(user.getId());

		assertEquals(1, userDocuments.size());
		assertEquals(document.getId(), userDocuments.get(0).getId());
		assertEquals(user, userDocuments.get(0).getUser());

		verify(userRepository, times(1)).findById(user.getId());
	}

	@Test(expected = RuntimeException.class)
	public void testGetUserDocuments_UserNotFound() {
		documentService.getUserDocuments(2L); // User with ID 2 does not exist
	}

	
}
