package com.example.dlbms.exceptions;

public class BookAlreadyCheckedOutException extends RuntimeException {

	public BookAlreadyCheckedOutException() {
		super();
	}

	public BookAlreadyCheckedOutException(String msg) {
		super(msg);
	}
}
