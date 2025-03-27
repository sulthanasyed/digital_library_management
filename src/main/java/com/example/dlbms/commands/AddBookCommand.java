package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class AddBookCommand implements ICommand {

	IBookService bookService;

	public AddBookCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String title = tokens.get(0);
		String author = tokens.get(1);
		String genre = tokens.get(2);
		AvailabilityStatus availability = AvailabilityStatus.valueOf(tokens.get(3));
		Book savedBook = bookService.addBookDetails(title, author, genre, availability);
		System.out.println("Book saved successfully!!");
		System.out.println(savedBook);
	}

}
