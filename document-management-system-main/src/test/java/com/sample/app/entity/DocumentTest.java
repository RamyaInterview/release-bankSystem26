package com.sample.app.entity;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DocumentTest {

	@Test
	public void testDocumentConstructorAndGetters() {
		// Create a sample user
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document
		Document document = new Document("test.pdf", "application/pdf", user);

		// Verify the values
		assertNull(document.getId());
		assertEquals("test.pdf", document.getFilename());
		assertEquals("application/pdf", document.getMimeType());
		assertArrayEquals(null, document.getContent());
		assertEquals(user, document.getUser());
	}

	@Test
	public void testDocumentSetters() {
		// Create a sample user
		User user = new User();
		user.setId(1L);
		user.setName("John Doe");

		// Create a sample document
		Document document = new Document();

		// Set the values
		document.setId(1L);
		document.setFilename("test.pdf");
		document.setMimeType("application/pdf");
		byte[] content = { 1, 2, 3 };
		document.setContent(content);
		document.setUser(user);

		// Verify the values
		assertEquals(1L, (long) document.getId());
		assertEquals("test.pdf", document.getFilename());
		assertEquals("application/pdf", document.getMimeType());
		assertArrayEquals(content, document.getContent());
		assertEquals(user, document.getUser());
	}

	@Test
	public void testDocumentNoArgsConstructor() {
		// Create a sample document using the no-args constructor
		Document document = new Document();

		// Verify the values are null
		assertNull(document.getId());
		assertNull(document.getFilename());
		assertNull(document.getMimeType());
		assertNull(document.getContent());
		assertNull(document.getUser());
	}

}
