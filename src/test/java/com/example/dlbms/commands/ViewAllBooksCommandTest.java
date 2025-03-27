package com.example.dlbms.commands;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;
import com.example.dlbms.services.BookService;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewAllBooksCommandTest {

	private IBookService bookService;
	private ViewAllBooksCommand viewAllBooksCommand;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	void setup() {
		IBookRepository bookRepository = new BookRepository();
		bookService = new BookService(bookRepository);
		viewAllBooksCommand = new ViewAllBooksCommand(bookService);

		System.setOut(new PrintStream(outputStreamCaptor));

		bookRepository.save(new Book("Book One", "Author One", "Fiction", AvailabilityStatus.AVAILABLE));
		bookRepository.save(new Book("Book Two", "Author Two", "Science", AvailabilityStatus.CHECKED_OUT));
	}

	@Test
	void testViewAllBooksCommand() {
		List<String> tokens = Arrays.asList(); // No tokens needed for this command

		viewAllBooksCommand.execute(tokens);

		String expectedOutput = "Book{id='1', title='Book One', author='Author One', genre='Fiction', availability='Available'}\n"
				+ "Book{id='2', title='Book Two', author='Author Two', genre='Science', availability='Checked Out'}\n";

		assertEquals(expectedOutput, outputStreamCaptor.toString());
	}
}
