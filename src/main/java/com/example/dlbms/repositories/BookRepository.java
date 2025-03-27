package com.example.dlbms.repositories;

import java.util.*;
import com.example.dlbms.entities.Book;

public class BookRepository implements IBookRepository {
	private final Map<String, Book> bookMap;
	private Integer autoIncrement = 0;

	public BookRepository() {
		bookMap = new HashMap<>();
	}

	@Override
	public Book save(Book entity) {
		if (entity.getId() == null) {
			autoIncrement++;
			Book book = new Book(Integer.toString(autoIncrement), entity.getTitle(), entity.getAuthor(),
					entity.getGenre(), entity.getBookAvailability());
			bookMap.put(book.getId(), book);
			return book;
		}
		bookMap.put(entity.getId(), entity);
		return entity;

	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(this.bookMap.values());
	}

	@Override
	public Optional<Book> findById(String id) {
		return Optional.ofNullable(bookMap.get(id));
	}

	@Override
	public Optional<Book> findByTitle(String title) {
		return bookMap.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
	}

	@Override
	public boolean existsById(String id) {
		return bookMap.containsKey(id);
	}

	@Override
	public void delete(Book entity) {
		if (bookMap.containsValue(entity)) {
			bookMap.remove(entity.getId());
		}
	}

	@Override
	public void deleteById(String id) {
		if (bookMap.containsKey(id)) {
			bookMap.remove(id);
		}
	}

	@Override
	public long count() {
		return bookMap.size();
	}

}
