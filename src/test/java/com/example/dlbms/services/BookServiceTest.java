package com.example.dlbms.services;

import com.example.dlbms.entities.Book;
import com.example.dlbms.entities.AvailabilityStatus;
import com.example.dlbms.exceptions.BookAlreadyCheckedOutException;
import com.example.dlbms.exceptions.BookNotFoundException;
import com.example.dlbms.repositories.BookRepository;
import com.example.dlbms.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BookServiceTest {

    private IBookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        bookService = new BookService(bookRepository);
    }

    @Test
    void testAddBookDetails() {
        String title = "Book Title";
        String author = "Author Name";
        String genre = "Genre";
        AvailabilityStatus availability = AvailabilityStatus.AVAILABLE;

        Book addedBook = bookService.addBookDetails(title, author, genre, availability);

        assertNotNull(addedBook);
        assertEquals(title, addedBook.getTitle());
        assertEquals(author, addedBook.getAuthor());
        assertEquals(genre, addedBook.getGenre());
        assertEquals(availability, addedBook.getBookAvailability());
    }

    @Test
    void testViewBooks() {
        Book book1 = new Book("Title1", "Author1", "Genre1", AvailabilityStatus.AVAILABLE);
        Book book2 = new Book("Title2", "Author2", "Genre2", AvailabilityStatus.CHECKED_OUT);

        bookService.addBookDetails(book1.getTitle(), book1.getAuthor(), book1.getGenre(), book1.getBookAvailability());
        bookService.addBookDetails(book2.getTitle(), book2.getAuthor(), book2.getGenre(), book2.getBookAvailability());

        List<Book> books = bookService.viewBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @Test
    void testSearchById_Found() {
        Book book = new Book("1", "Book Title", "Author Name", "Genre", AvailabilityStatus.AVAILABLE);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        Book foundBook = bookService.searchById(book.getId());

        assertNotNull(foundBook);
        assertEquals(book.getId(), foundBook.getId());
        assertEquals(book.getTitle(), foundBook.getTitle());
    }

    @Test
    void testSearchById_NotFound() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.searchById("non-existing-id");
        });

        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    void testSearchByTitle_Found() {
        Book book = new Book("Book Title", "Author Name", "Genre", AvailabilityStatus.AVAILABLE);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        Book foundBook = bookService.searchByTitle(book.getTitle());

        assertNotNull(foundBook);
        assertEquals(book.getTitle(), foundBook.getTitle());
    }

    @Test
    void testSearchByTitle_NotFound() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.searchByTitle("Non-existing Title");
        });

        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    void testUpdateBookDetails() {
        Book book = new Book("1", "Old Title", "Old Author", "Old Genre", AvailabilityStatus.AVAILABLE);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        String newTitle = "Updated Title";
        String newAuthor = "Updated Author";
        String newGenre = "Updated Genre";

        Book updatedBook = bookService.updateBookDetails(book.getId(), newTitle, newAuthor, newGenre);

        assertNotNull(updatedBook);
        assertEquals(newTitle, updatedBook.getTitle());
        assertEquals(newAuthor, updatedBook.getAuthor());
        assertEquals(newGenre, updatedBook.getGenre());
    }

    @Test
    void testUpdateBookDetails_NotFound() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.updateBookDetails("non-existing-id", "New Title", "New Author", "New Genre");
        });

        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    void testUpdateBookStatus() {
        Book book = new Book("1", "Book Title", "Author Name", "Genre", AvailabilityStatus.AVAILABLE);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        AvailabilityStatus newStatus = AvailabilityStatus.CHECKED_OUT;

        Book updatedBook = bookService.updateBookStatus(book.getId(), newStatus);

        assertNotNull(updatedBook);
        assertEquals(newStatus, updatedBook.getBookAvailability());
    }

    @Test
    void testUpdateBookStatus_NotFound() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.updateBookStatus("non-existing-id", AvailabilityStatus.CHECKED_OUT);
        });

        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    void testUpdateBookStatus_AlreadyCheckedOut() {
        Book book = new Book("1", "Book Title", "Author Name", "Genre", AvailabilityStatus.CHECKED_OUT);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        Exception exception = assertThrows(BookAlreadyCheckedOutException.class, () -> {
            bookService.updateBookStatus(book.getId(), AvailabilityStatus.CHECKED_OUT);
        });

        assertEquals("Book is already checkedout from the library.", exception.getMessage());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("1", "Book Title", "Author Name", "Genre", AvailabilityStatus.AVAILABLE);
        bookService.addBookDetails(book.getTitle(), book.getAuthor(), book.getGenre(), book.getBookAvailability());

        bookService.deleteBook(book.getId());

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.searchById(book.getId());
        });
        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    void testDeleteBook_NotFound() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.deleteBook("non-existing-id");
        });

        assertEquals("Book Not Found", exception.getMessage());
    }
}
