package com.qaforum.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * 
 * @author cdacr
 *
 */
public class ApplicationConfig extends ResourceConfig {

	/**
	 * 
	 */
	public ApplicationConfig() {
		register(QaInfoRest.class);
		register(LoginInfoRest.class);
	}

}