package com.qaforum.validation;

import java.util.List;

/**
 * 
 * @author cdacr 
 *
 */
public interface QaInfoValidation {

	/**
	 * 
	 * @param fileName 
	 * @return List of error messages
	 */
	List<String> validateQaImageData(String fileName);

}
