package com.example.dlbms.exceptions;

public class NoSuchCommandException extends RuntimeException {

	public NoSuchCommandException() {
		super();
	}

	public NoSuchCommandException(String msg) {
		super(msg);
	}

}
