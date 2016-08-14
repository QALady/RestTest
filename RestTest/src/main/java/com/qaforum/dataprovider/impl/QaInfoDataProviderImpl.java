/**
 * 
 */
package com.qaforum.dataprovider.impl;

import java.util.List;

import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.dataprovider.QaInfoDataProvider;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.exception.InvalidDataException;

/**
 * @author cdacr
 *
 */
public final class QaInfoDataProviderImpl implements QaInfoDataProvider {

	@Override
	public QaInfoDTO save(final QaInfoDTO qaInfoDto)
			throws InvalidDataException {
		final QaInfoBO qaBo = CONVERTER.convertQaInfoDTOToBO(qaInfoDto);
		return CONVERTER.convertQaInfoBOToDTO(QA_SERVICE.save(qaBo));
	}

	@Override
	public void delete(final Long qaId) {
		QA_SERVICE.delete(qaId);
	}

	@Override
	public List<QaInfoDTO> findAll() {
		return CONVERTER.convertListQaBOToDTO(QA_SERVICE.findAll());
	}

	@Override
	public List<QaInfoDTO> findSelected(final QaInfoSearchDTO searchDto) {
		final QaInfoSearchBO searchBo = CONVERTER
				.convertSearchDTOToBO(searchDto);
		return CONVERTER
				.convertListQaBOToDTO(QA_SERVICE.findSelected(searchBo));
	}

	@Override
	public List<QaInfoDTO> saveBatch(final List<QaInfoDTO> qaInfoDtos) {
		final List<QaInfoBO> qaBos = CONVERTER.convertListQaDTOToBO(qaInfoDtos);
		return CONVERTER.convertListQaBOToDTO(QA_SERVICE.saveBatch(qaBos));
	}
}
