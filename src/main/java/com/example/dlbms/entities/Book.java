package com.example.dlbms.entities;

import java.util.Objects;

public class Book extends BaseEntity {

	private String title;
	private String author;
	private String genre;
	private AvailabilityStatus bookAvailability;

	public Book(String title, String author, String genre, AvailabilityStatus bookAvailability) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setGenre(genre);
		this.setBookAvailability(bookAvailability);
	}

	public Book(String bookId, String title, String author, String genre, AvailabilityStatus bookAvailability) {
		this(title, author, genre, bookAvailability);
		this.id = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public AvailabilityStatus getBookAvailability() {
		return bookAvailability;
	}

	public void setBookAvailability(AvailabilityStatus bookAvailability) {
		this.bookAvailability = bookAvailability;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", bookAvailability="
				+ bookAvailability + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, bookAvailability, genre, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && bookAvailability == other.bookAvailability
				&& Objects.equals(genre, other.genre) && Objects.equals(title, other.title);
	}

}
