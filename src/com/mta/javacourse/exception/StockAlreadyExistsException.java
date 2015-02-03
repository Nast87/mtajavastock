package com.mta.javacourse.exception;

public class StockAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol) {
		super("Sorry, the stock " +symbol+ " already exists at the portfolio!");
	}
}
