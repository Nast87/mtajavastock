package com.mta.javacourse.exception;

public class QuantityException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public QuantityException (String symbol) {
		super("Sorry, not enough quantiy " +symbol+ " stocks in the portfolio!");
	}
}
