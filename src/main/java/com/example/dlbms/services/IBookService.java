package com.example.dlbms.services;

import java.util.List;

import com.example.dlbms.entities.Book;
import com.example.dlbms.entities.AvailabilityStatus;

public interface IBookService {

	Book addBookDetails(String title, String author, String genre, AvailabilityStatus availability);

	List<Book> viewBooks();

	Book searchById(String bookId);

	Book searchByTitle(String title);

	Book updateBookDetails(String id, String title, String author, String genre);

	Book updateBookStatus(String id, AvailabilityStatus bookAvailability);

	void deleteBook(String id);
}
