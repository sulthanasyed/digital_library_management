package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchByTitleCommandTest {

	private IBookService bookService;
	private SearchByTitleCommand searchByTitleCommand;

	@BeforeEach
	void setup() {
		bookService = new BookService(new BookRepository()); // Assuming BookRepository is a simple in-memory repo
		searchByTitleCommand = new SearchByTitleCommand(bookService);

		bookService.addBookDetails("Test Book", "Test Author", "Fiction", AvailabilityStatus.AVAILABLE);
	}

	@Test
	void testSearchByTitleCommand_Success() {
		List<String> tokens = Arrays.asList("Test Book");

		searchByTitleCommand.execute(tokens);

		Book book = bookService.searchByTitle("Test Book");
		assertNotNull(book); // Ensure the book exists
		assertEquals("Test Book", book.getTitle());
		assertEquals("Test Author", book.getAuthor());
	}

	@Test
	void testSearchByTitleCommand_BookNotFoundException() {
		List<String> tokens = Arrays.asList("Nonexistent Book");

		assertThrows(BookNotFoundException.class, () -> {
			searchByTitleCommand.execute(tokens);
		});
	}
}
