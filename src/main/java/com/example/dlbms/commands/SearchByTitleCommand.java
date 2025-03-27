package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class SearchByTitleCommand implements ICommand {

	private IBookService bookService;

	public SearchByTitleCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String title = tokens.get(0);
		Book searchedBook = bookService.searchByTitle(title);
		System.out.println(searchedBook);
	}

}
