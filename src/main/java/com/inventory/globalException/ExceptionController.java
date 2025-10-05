package com.inventory.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.exceptions.InvalidStockOperationException;
import com.inventory.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundWithId(ProductNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = InvalidStockOperationException.class)
	public ResponseEntity<String> invalidStockOperation(InvalidStockOperationException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<String> handleOtherException(RuntimeException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
