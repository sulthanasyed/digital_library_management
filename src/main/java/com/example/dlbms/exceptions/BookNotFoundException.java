package com.example.dlbms.exceptions;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException() {
		super();
	}

	public BookNotFoundException(String msg) {
		super(msg);
	}

}
