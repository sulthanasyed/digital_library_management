package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class UpdateBookAvailabilityCommand implements ICommand {

	private IBookService bookService;

	public UpdateBookAvailabilityCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String id = tokens.get(0);
		AvailabilityStatus availability = AvailabilityStatus.valueOf(tokens.get(1));
		Book updatedBook = bookService.updateBookStatus(id, availability);
		System.out.println("Book availability updated successfully!!");
		System.out.println(updatedBook);
	}

}
