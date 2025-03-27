package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.exceptions.BookAlreadyCheckedOutException;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookAvailabilityCommandTest {

	private IBookService bookService;
	private UpdateBookAvailabilityCommand updateBookAvailabilityCommand;

	@BeforeEach
	void setup() {
		IBookRepository bookRepository = new BookRepository();
		bookService = new BookService(bookRepository);
		updateBookAvailabilityCommand = new UpdateBookAvailabilityCommand(bookService);

		bookService.addBookDetails("Test Book", "Test Author", "Fiction", AvailabilityStatus.AVAILABLE);
	}

	@Test
	void testUpdateBookAvailabilityCommand_Success() {
		String bookId = "1";
		List<String> tokens = Arrays.asList(bookId, "CHECKED_OUT");

		updateBookAvailabilityCommand.execute(tokens);

		Book updatedBook = bookService.searchById(bookId);
		assertNotNull(updatedBook); // Ensure the book is found
		assertEquals(AvailabilityStatus.CHECKED_OUT, updatedBook.getBookAvailability());
	}

	@Test
	void testUpdateBookAvailabilityCommand_BookNotFoundException() {
		List<String> tokens = Arrays.asList("999", "CHECKED_OUT");

		assertThrows(BookNotFoundException.class, () -> {
			updateBookAvailabilityCommand.execute(tokens);
		});
	}

	@Test
	void testUpdateBookAvailabilityCommand_BookAlreadyCheckedOutException() {
		String bookId = "1";
		bookService.updateBookStatus(bookId, AvailabilityStatus.CHECKED_OUT); // Set the initial status to CHECKED_OUT

		List<String> tokens = Arrays.asList(bookId, "CHECKED_OUT");

		assertThrows(BookAlreadyCheckedOutException.class, () -> {
			updateBookAvailabilityCommand.execute(tokens);
		});
	}
}
