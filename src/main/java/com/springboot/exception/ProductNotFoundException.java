package com.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -3204705332368137965L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
