/*
* Copyright 2018 Builders
*************************************************************
*Nome     : NotFoundException.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends APIException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(final Throwable cause) {
		super(HttpStatus.NOT_FOUND, cause);
	}

	public NotFoundException(final String msg) {
		super(HttpStatus.NOT_FOUND, new Throwable(msg));
	}
}

