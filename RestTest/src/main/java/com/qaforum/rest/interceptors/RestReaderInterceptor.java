/**
 * 
 */
package com.qaforum.rest.interceptors;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * @author cdacr
 * 
 */
@Provider
public class RestReaderInterceptor implements ReaderInterceptor {
	Logger LOGGER = Logger.getLogger(getClass().getName());

	@Override
	public Object aroundReadFrom(final ReaderInterceptorContext context)
			throws IOException, WebApplicationException {
		LOGGER.info("Class : " + context.getClass().getName());
		LOGGER.info("Media Type : " + context.getMediaType().getType());
		LOGGER.info("Headers : " + context.getHeaders().entrySet());
		LOGGER.info("Property Names : " + context.getPropertyNames());
		return context.proceed();
	}

}
