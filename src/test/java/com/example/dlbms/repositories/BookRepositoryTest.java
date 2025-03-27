package com.example.dlbms.repositories;

import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class BookRepositoryTest {
	private BookRepository bookRepository;

	@BeforeEach
	void setup() {
		bookRepository = new BookRepository();
		bookRepository.save(new Book("Book 1", "Author 1", "Genre 1", AvailabilityStatus.AVAILABLE));
		bookRepository.save(new Book("Book 2", "Author 2", "Genre 2", AvailabilityStatus.AVAILABLE));
		bookRepository.save(new Book("Book 3", "Author 3", "Genre 3", AvailabilityStatus.AVAILABLE));
	}

	@Test
	@DisplayName("save method should create and return new Book")
	public void saveBook() {
		Book newBook = new Book("Book 4", "Author 4", "Genre 4", AvailabilityStatus.AVAILABLE);
		Book expectedBook = new Book("4", "Book 4", "Author 4", "Genre 4", AvailabilityStatus.AVAILABLE);

		Book actualBook = bookRepository.save(newBook);

		Assertions.assertEquals(expectedBook, actualBook);
	}

	@Test
	@DisplayName("findAll method should return all books")
	public void findAllBooks() {
		int expectedCount = 3;

		List<Book> actualBooks = bookRepository.findAll();

		Assertions.assertEquals(expectedCount, actualBooks.size());
	}

	@Test
	@DisplayName("findAll method should return empty list if no books are present")
	public void findAllBooks_ShouldReturnEmptyList() {
		BookRepository emptyBookRepository = new BookRepository();
		int expectedCount = 0;

		List<Book> actualBooks = emptyBookRepository.findAll();

		Assertions.assertEquals(expectedCount, actualBooks.size());
	}

	@Test
	@DisplayName("findById method should return book given its ID")
	public void findById_ShouldReturnBook_GivenBookId() {
		String expectedBookId = "2";

		Optional<Book> actualBook = bookRepository.findById(expectedBookId);

		Assertions.assertTrue(actualBook.isPresent());
		Assertions.assertEquals(expectedBookId, actualBook.get().getId());
	}

	@Test
	@DisplayName("findById method should return empty if book not found")
	public void findById_ShouldReturnEmptyIfBookNotFound() {
		Optional<Book> expected = Optional.empty();

		Optional<Book> actual = bookRepository.findById("5");

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("findByTitle method should return book given its title")
	public void findByTitle_ShouldReturnBook_GivenBookTitle() {
		String expectedTitle = "Book 1";

		Optional<Book> actualBook = bookRepository.findByTitle(expectedTitle);

		Assertions.assertTrue(actualBook.isPresent());
		Assertions.assertEquals(expectedTitle, actualBook.get().getTitle());
	}

	@Test
	@DisplayName("findByTitle method should return empty if book not found by title")
	public void findByTitle_ShouldReturnEmptyIfBookNotFoundByTitle() {
		Optional<Book> expected = Optional.empty();

		Optional<Book> actual = bookRepository.findByTitle("Book 5");

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("delete method should remove the book from repository")
	public void deleteBook_ShouldRemoveBook() {
		String bookId = "1";
		int initialSize = bookRepository.findAll().size();

		Book bookToDelete = bookRepository.findById(bookId).get();
		bookRepository.delete(bookToDelete);

		int updatedSize = bookRepository.findAll().size();
		Assertions.assertEquals(initialSize - 1, updatedSize);
	}

	@Test
	@DisplayName("deleteById method should remove the book by its ID")
	public void deleteById_ShouldRemoveBook_GivenBookId() {
		String bookId = "2";
		int initialSize = bookRepository.findAll().size();

		bookRepository.deleteById(bookId);

		int updatedSize = bookRepository.findAll().size();
		Assertions.assertEquals(initialSize - 1, updatedSize);
	}

	@Test
	@DisplayName("existsById method should return true if book exists by ID")
	public void existsById_ShouldReturnTrue_IfBookExists() {
		String existingBookId = "3";

		boolean exists = bookRepository.existsById(existingBookId);

		Assertions.assertTrue(exists);
	}

	@Test
	@DisplayName("existsById method should return false if book does not exist by ID")
	public void existsById_ShouldReturnFalse_IfBookDoesNotExist() {
		String nonExistingBookId = "5";

		boolean exists = bookRepository.existsById(nonExistingBookId);

		Assertions.assertFalse(exists);
	}

	@Test
	@DisplayName("count method should return the number of books in the repository")
	public void count_ShouldReturnCorrectBookCount() {
		long expectedCount = 3;

		long actualCount = bookRepository.count();

		Assertions.assertEquals(expectedCount, actualCount);
	}
}
