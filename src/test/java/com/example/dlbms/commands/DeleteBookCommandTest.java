package com.example.dlbms.commands;

import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;
import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.repositories.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DeleteBookCommandTest {

	private IBookService bookService;
	private DeleteBookCommand deleteBookCommand;

	@BeforeEach
	void setup() {
		bookService = new BookService(new BookRepository()); // Assuming BookRepository is a simple in-memory repo
		deleteBookCommand = new DeleteBookCommand(bookService);

		bookService.addBookDetails("Test Book", "Test Author", "Fiction", AvailabilityStatus.AVAILABLE);
	}

	@Test
	void testDeleteBookCommand_Success() {
		List<String> tokens = Arrays.asList("1");

		deleteBookCommand.execute(tokens);

		assertThrows(BookNotFoundException.class, () -> {
			bookService.searchById("1"); // This should throw an exception because the book is deleted
		});
	}

	@Test
	void testDeleteBookCommand_BookNotFoundException() {
		List<String> tokens = Arrays.asList("999");

		assertThrows(BookNotFoundException.class, () -> {
			deleteBookCommand.execute(tokens);
		});
	}
}
