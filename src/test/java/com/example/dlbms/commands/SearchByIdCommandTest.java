package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class SearchByIdCommandTest {

	private IBookService bookService;
	private SearchByIdCommand searchByIdCommand;

	@BeforeEach
	void setup() {
		bookService = new BookService(new BookRepository()); // Assuming BookRepository is a simple in-memory repo
		searchByIdCommand = new SearchByIdCommand(bookService);

		bookService.addBookDetails("Test Book", "Test Author", "Fiction", AvailabilityStatus.AVAILABLE);
	}

	@Test
	void testSearchByIdCommand_Success() {
		List<String> tokens = Arrays.asList("1");

		searchByIdCommand.execute(tokens);

		Book book = bookService.searchById("1");
		assertNotNull(book); // Ensure the book exists
		assertEquals("Test Book", book.getTitle());
	}

	@Test
	void testSearchByIdCommand_BookNotFoundException() {
		List<String> tokens = Arrays.asList("999");
		assertThrows(BookNotFoundException.class, () -> {
			searchByIdCommand.execute(tokens);
		});
	}
}
