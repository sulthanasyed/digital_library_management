package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class UpdateBookDetailsCommand implements ICommand {

	private IBookService bookService;

	public UpdateBookDetailsCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String id = tokens.get(0);
		String title = tokens.get(1);
		String author = tokens.get(2);
		String genre = tokens.get(3);
		Book updatedBook = bookService.updateBookDetails(id, title, author, genre);
		System.out.println("Book details updated successfully!!");
		System.out.println(updatedBook);
	}

}
