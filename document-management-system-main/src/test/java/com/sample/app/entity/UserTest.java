package com.sample.app.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUserGettersAndSetters() {
		// Create a sample user
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document
		Document document = new Document();
		document.setId(1L);
		document.setFilename("test.pdf");
		document.setMimeType("application/pdf");

		// Add the document to the user's list of documents
		List<Document> documents = new ArrayList<>();
		documents.add(document);
		user.setDocuments(documents);

		// Verify the values
		assertEquals(1L, (long) user.getId());
		assertEquals("John Doe", user.getName());
		assertEquals(documents, user.getDocuments());
	}

	@Test
	public void testUserNoArgsConstructor() {
		// Create a sample user using the no-args constructor
		User user = new User();

		// Verify the values are null or empty
		assertNull(user.getId());
		assertNull(user.getName());
		assertNotNull(user.getDocuments());
		assertTrue(user.getDocuments().isEmpty());
	}

	@Test
	public void testUserAddDocument() {
		// Create a sample user
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document
		Document document = new Document();
		document.setId(1L);
		document.setFilename("test.pdf");
		document.setMimeType("application/pdf");

		// Add the document to the user's list of documents
		user.getDocuments().add(document);

		// Verify the document is added
		assertEquals(1, user.getDocuments().size());
		assertEquals(document, user.getDocuments().get(0));
	}

	@Test
	public void testUserRemoveDocument() {
		// Create a sample user
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document
		Document document = new Document();
		document.setId(1L);
		document.setFilename("test.pdf");
		document.setMimeType("application/pdf");

		// Add the document to the user's list of documents
		user.getDocuments().add(document);

		// Remove the document from the user's list of documents
		user.getDocuments().remove(document);

		// Verify the document is removed
		assertTrue(user.getDocuments().isEmpty());
	}

}
