package com.example.dlbms.commands;

import java.util.List;

import com.example.dlbms.entities.Book;
import com.example.dlbms.services.IBookService;

public class SearchByIdCommand implements ICommand {

	private IBookService bookService;

	public SearchByIdCommand(IBookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void execute(List<String> tokens) {
		String id = tokens.get(0);
		Book searchedBook = bookService.searchById(id);
		System.out.println(searchedBook);
	}

}
