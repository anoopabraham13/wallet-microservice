package com.uk.leovegas.wallet.exception;

/**
 * This is an Exception to handle the error when the transaction is invalid.
 * 
 * @author Anoop Abraham
 *
 */
public class InvalidTransactionException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param message
	 */
	public InvalidTransactionException(String message) {
		super(message);
	}

}
