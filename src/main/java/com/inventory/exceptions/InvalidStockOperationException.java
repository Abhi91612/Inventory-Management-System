package com.inventory.exceptions;



public class InvalidStockOperationException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public InvalidStockOperationException(String msg) {
		super (msg);
	}

}
