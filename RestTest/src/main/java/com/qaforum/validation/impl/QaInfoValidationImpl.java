/**
 * 
 */
package com.qaforum.validation.impl;

import java.util.ArrayList;
import java.util.List;

import com.qaforum.util.MessageConstants;
import com.qaforum.validation.QaInfoValidation;

/**
 * @author cdacr
 *
 */
public final class QaInfoValidationImpl implements QaInfoValidation {

	@Override
	public List<String> validateQaImageData(final String fileName) {
		final List<String> messages = new ArrayList<>();
		if (fileName == null || fileName.trim().equals("")) {
			messages.add(MessageConstants.IMG_FILE_NM_E_MSG);
		}
		return messages;
	}

}
