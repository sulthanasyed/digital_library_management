package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class ViewAllBooksCommand implements ICommand {

	private IBookService bookService;

	public ViewAllBooksCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		List<Book> booksList = bookService.viewBooks();
		for (Book eachBook : booksList) {
			System.out.println(eachBook);
		}
	}

}
