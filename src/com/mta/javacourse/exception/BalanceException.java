package com.mta.javacourse.exception;

public class BalanceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public BalanceException() {
		super("Your portfolio balance is negative!");
	} 
}
