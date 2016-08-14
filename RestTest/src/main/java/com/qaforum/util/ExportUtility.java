/**
 * 
 */
package com.qaforum.util;

import java.io.File;

import com.qaforum.converter.Converter;
import com.qaforum.converter.impl.ConverterImpl;
import com.qaforum.service.QaInfoService;
import com.qaforum.service.impl.QaInfoServiceImpl;

/**
 * @author cdacr
 * 
 */
public interface ExportUtility {

	/** */
	QaInfoService QA_SERVICE = new QaInfoServiceImpl();

	/** */
	Converter CONVERTER = new ConverterImpl();

	/**
	 * 
	 * @return {@link File}
	 */
	File generateExcel();

	/**
	 * 
	 * @return {@link File}
	 */
	File generatePdf();

	/**
	 * 
	 * @return {@link File}
	 */
	File generateWord();

}
