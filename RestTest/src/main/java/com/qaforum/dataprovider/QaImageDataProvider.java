/**
 * 
 */
package com.qaforum.dataprovider;

import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.qaforum.converter.Converter;
import com.qaforum.converter.impl.ConverterImpl;
import com.qaforum.dto.QaImageInfoDTO;
import com.qaforum.service.QaImageInfoService;
import com.qaforum.service.impl.QaImageInfoServiceImpl;
import com.qaforum.validation.QaInfoValidation;
import com.qaforum.validation.impl.QaInfoValidationImpl;

/**
 * @author cdacr
 *
 */
public interface QaImageDataProvider {
	/** */
	QaImageInfoService QA_IMG_SER = new QaImageInfoServiceImpl();
	/** */
	Converter CONVERTER = new ConverterImpl();

	/** */
	QaInfoValidation QA_VAL = new QaInfoValidationImpl();

	/**
	 * 
	 * @param infoDTO 
	 * @return {@link QaImageInfoDTO}
	 */
	QaImageInfoDTO save(QaImageInfoDTO infoDTO,
			FormDataContentDisposition fileMetaData);

	/**
	 * 
	 * @param imageId 
	 */
	void delete(Long imageId);

	/**
	 * 
	 * @return List of {@link QaImageInfoDTO}
	 */
	List<QaImageInfoDTO> findAll();

	/**
	 * 
	 * @param infoDTO 
	 * @return List of {@link QaImageInfoDTO}
	 */
	List<QaImageInfoDTO> findSelected(QaImageInfoDTO infoDTO);
}
