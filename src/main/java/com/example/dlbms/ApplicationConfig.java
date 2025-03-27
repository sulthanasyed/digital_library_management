package com.example.dlbms;

import com.example.dlbms.commands.AddBookCommand;
import com.example.dlbms.commands.CommandInvoker;
import com.example.dlbms.commands.DeleteBookCommand;
import com.example.dlbms.commands.SearchByIdCommand;
import com.example.dlbms.commands.SearchByTitleCommand;
import com.example.dlbms.commands.UpdateBookAvailabilityCommand;
import com.example.dlbms.commands.UpdateBookDetailsCommand;
import com.example.dlbms.commands.ViewAllBooksCommand;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.repositories.IBookRepository;
import com.example.dlbms.services.BookService;
import com.example.dlbms.services.IBookService;

public class ApplicationConfig {

	private final IBookRepository bookRepository = new BookRepository();

	private final IBookService bookService = new BookService(bookRepository);

	private final AddBookCommand addBookCommand = new AddBookCommand(bookService);
	private final ViewAllBooksCommand viewAllBooksCommand = new ViewAllBooksCommand(bookService);
	private final SearchByIdCommand searchByIdCommand = new SearchByIdCommand(bookService);
	private final SearchByTitleCommand searchByTitleCommand = new SearchByTitleCommand(bookService);
	private final UpdateBookDetailsCommand updateBookDetailsCommand = new UpdateBookDetailsCommand(bookService);
	private final UpdateBookAvailabilityCommand updateBookAvailabilityCommand = new UpdateBookAvailabilityCommand(
			bookService);
	private final DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookService);

	private final CommandInvoker commandInvoker = new CommandInvoker();

	public CommandInvoker getCommandInvoker() {
		commandInvoker.register("ADD_BOOK", addBookCommand);
		commandInvoker.register("VIEW_ALL", viewAllBooksCommand);
		commandInvoker.register("SEARCH_BOOK_ID", searchByIdCommand);
		commandInvoker.register("SEARCH_BOOK_TITLE", searchByTitleCommand);
		commandInvoker.register("UPDATE_BOOK_DETAILS", updateBookDetailsCommand);
		commandInvoker.register("UPDATE_BOOK_AVAILABILITY", updateBookAvailabilityCommand);
		commandInvoker.register("DELETE_BOOK", deleteBookCommand);
		return commandInvoker;
	}
}
