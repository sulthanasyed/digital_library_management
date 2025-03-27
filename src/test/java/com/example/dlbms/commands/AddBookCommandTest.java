package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import com.example.dlbms.repositories.IBookRepository;
import com.example.dlbms.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddBookCommandTest {

	private IBookService bookService;
	private AddBookCommand addBookCommand;

	@BeforeEach
	void setup() {
		IBookRepository bookRepository = new BookRepository();
		bookService = new BookService(bookRepository);
		addBookCommand = new AddBookCommand(bookService);
	}

	@Test
	void testAddBookCommand_Success() {
		List<String> tokens = Arrays.asList("Test Book", "Test Author", "Fiction", "AVAILABLE");

		addBookCommand.execute(tokens);

		Book addedBook = bookService.searchByTitle("Test Book");
		assertNotNull(addedBook); // Ensure the book was added
		assertEquals("Test Book", addedBook.getTitle());
		assertEquals("Test Author", addedBook.getAuthor());
		assertEquals("Fiction", addedBook.getGenre());
		assertEquals(AvailabilityStatus.AVAILABLE, addedBook.getBookAvailability());
	}

	@Test
	void testAddBookCommand_InvalidAvailabilityStatus() {
		List<String> tokens = Arrays.asList("Test Book", "Test Author", "Fiction", "UNAVAILABLE");

		assertThrows(IllegalArgumentException.class, () -> {
			addBookCommand.execute(tokens);
		});
	}
}
