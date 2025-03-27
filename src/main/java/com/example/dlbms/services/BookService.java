package com.example.dlbms.services;

import java.util.List;
import java.util.Optional;
import com.example.dlbms.entities.Book;
import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.exceptions.BookAlreadyCheckedOutException;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.exceptions.SystemException;
import com.example.dlbms.repositories.IBookRepository;

public class BookService implements IBookService {

	private IBookRepository bookRepository;

	public BookService(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book addBookDetails(String title, String author, String genre, AvailabilityStatus availability) {
		if(title == null || title.trim() == "") {
			throw new SystemException("Title cannot be empty");
		}
		if(author == null || author.trim() == "") {
			throw new SystemException("Author cannot be empty");
		}
		Book b = new Book(title, author, genre, availability);
		return bookRepository.save(b);
	}

	@Override
	public List<Book> viewBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book searchById(String bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		return book.get();
	}

	@Override
	public Book searchByTitle(String title) {
		Optional<Book> book = bookRepository.findByTitle(title);
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		return book.get();
	}

	@Override
	public Book updateBookDetails(String id, String title, String author, String genre) {
		if(title == null || title.trim() == "") {
			throw new SystemException("Title cannot be empty");
		}
		if(author == null || author.trim() == "") {
			throw new SystemException("Author cannot be empty");
		}
		Optional<Book> bookFromRepo = bookRepository.findById(id);
		if (!bookFromRepo.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		Book bookToUpdate = bookFromRepo.get();
		bookToUpdate.setTitle(title);
		bookToUpdate.setAuthor(author);
		bookToUpdate.setGenre(genre);
		return bookRepository.save(bookToUpdate);
	}

	@Override
	public Book updateBookStatus(String id, AvailabilityStatus bookAvailability) {
		Optional<Book> bookFromRepo = bookRepository.findById(id);
		if (!bookFromRepo.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		Book bookToUpdate = bookFromRepo.get();
		if (bookToUpdate.getBookAvailability() == AvailabilityStatus.CHECKED_OUT
				&& bookAvailability == AvailabilityStatus.CHECKED_OUT) {
			throw new BookAlreadyCheckedOutException("Book is already checkedout from the library.");
		}
		bookToUpdate.setBookAvailability(bookAvailability);
		return bookRepository.save(bookToUpdate);
	}

	@Override
	public void deleteBook(String id) {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		bookRepository.deleteById(id);
	}

}
