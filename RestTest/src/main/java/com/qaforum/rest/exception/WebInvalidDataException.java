package com.qaforum.rest.exception;

import java.util.List;

import javax.ws.rs.WebApplicationException;

/**
 * 
 * @author cdacr 
 *
 */
public class WebInvalidDataException extends WebApplicationException {

	/**	 */
	private static final long serialVersionUID = 3263550056762601686L;

	/**
	 * 
	 * @param message 
	 */
	public WebInvalidDataException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param messages 
	 */
	public WebInvalidDataException(final List<String> messages) {
		final StringBuilder errorMessage = new StringBuilder();
		for (final String message : messages) {
			errorMessage.append(message).append("\n");
		}
		new WebInvalidDataException(errorMessage.toString());
	}
}
