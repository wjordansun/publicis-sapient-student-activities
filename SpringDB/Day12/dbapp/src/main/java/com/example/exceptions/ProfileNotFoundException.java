package com.example.exceptions;

/*
 * Checked exception must extend Exception class and you must have overloaded constructor
 * that takes message as an argument
 */
@SuppressWarnings("serial")
public class ProfileNotFoundException extends Exception {

	public ProfileNotFoundException() {
		super();
	}

	public ProfileNotFoundException(String message) {
		super(message);
	}	
}
