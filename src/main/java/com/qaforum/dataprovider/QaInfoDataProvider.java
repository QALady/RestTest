/**
 * 
 */
package com.qaforum.dataprovider;

import java.util.List;

import com.qaforum.converter.Converter;
import com.qaforum.converter.impl.ConverterImpl;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.exception.InvalidDataException;
import com.qaforum.service.QaInfoService;
import com.qaforum.service.impl.QaInfoServiceImpl;

/**
 * @author cdacr
 *
 */
public interface QaInfoDataProvider {
	/** */
	QaInfoService QA_SERVICE = new QaInfoServiceImpl();
	/** */
	Converter CONVERTER = new ConverterImpl();

	/**
	 * 
	 * @param qaInfoDto 
	 * @return {@link QaInfoDTO}
	 * @throws InvalidDataException 
	 */
	QaInfoDTO save(final QaInfoDTO qaInfoDto) throws InvalidDataException;

	/**
	 * 
	 * @param qaId 
	 */
	void delete(final Long qaId);

	/**
	 * 
	 * @return List of {@link QaInfoDTO}
	 */
	List<QaInfoDTO> findAll();

	/**
	 * 
	 * @param searchDto 
	 * @return List of {@link QaInfoDTO}
	 */
	List<QaInfoDTO> findSelected(final QaInfoSearchDTO searchDto);

	/**
	 * 
	 * @param qaInfoDtos 
	 * @return List of {@link QaInfoDTO}
	 */
	List<QaInfoDTO> saveBatch(final List<QaInfoDTO> qaInfoDtos);
}
