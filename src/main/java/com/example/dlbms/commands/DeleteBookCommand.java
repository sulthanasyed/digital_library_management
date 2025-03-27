package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.services.IBookService;

public class DeleteBookCommand implements ICommand {

	private IBookService bookService;

	public DeleteBookCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String id = tokens.get(0);
		bookService.deleteBook(id);
		System.out.println("Book deleted successfully!!");
	}

}
