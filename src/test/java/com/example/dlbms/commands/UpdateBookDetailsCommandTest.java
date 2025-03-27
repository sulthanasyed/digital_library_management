package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookDetailsCommandTest {

	private IBookService bookService;
	private UpdateBookDetailsCommand updateBookDetailsCommand;

	@BeforeEach
	void setup() {
		IBookRepository bookRepository = new BookRepository();
		bookService = new BookService(bookRepository);
		updateBookDetailsCommand = new UpdateBookDetailsCommand(bookService);

		bookService.addBookDetails("Test Book", "Test Author", "Fiction", AvailabilityStatus.AVAILABLE);
	}

	@Test
	void testUpdateBookDetailsCommand_Success() {
		String bookId = "1"; // Assuming "1" is the book ID from the repository after it is added
		List<String> tokens = Arrays.asList(bookId, "Updated Title", "Updated Author", "Updated Genre");

		updateBookDetailsCommand.execute(tokens);

		Book updatedBook = bookService.searchById(bookId);
		assertNotNull(updatedBook);
		assertEquals("Updated Title", updatedBook.getTitle());
		assertEquals("Updated Author", updatedBook.getAuthor());
		assertEquals("Updated Genre", updatedBook.getGenre());
	}

	@Test
	void testUpdateBookDetailsCommand_BookNotFoundException() {
		List<String> tokens = Arrays.asList("999", "New Title", "New Author", "New Genre");

		assertThrows(BookNotFoundException.class, () -> {
			updateBookDetailsCommand.execute(tokens);
		});
	}
}
