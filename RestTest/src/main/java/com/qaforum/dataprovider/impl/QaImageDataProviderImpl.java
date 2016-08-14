/**
 * 
 */
package com.qaforum.dataprovider.impl;

import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.qaforum.bo.QaImageInfoBO;
import com.qaforum.dataprovider.QaImageDataProvider;
import com.qaforum.dto.QaImageInfoDTO;
import com.qaforum.rest.exception.WebInvalidDataException;

/**
 * @author cdacr
 *
 */
public final class QaImageDataProviderImpl implements QaImageDataProvider {

	@Override
	public QaImageInfoDTO save(final QaImageInfoDTO infoDto,
			final FormDataContentDisposition fileMetaData) {
		final String fileName = fileMetaData.getFileName();
		final List<String> messages = QA_VAL.validateQaImageData(fileName);
		if (messages.size() > 0) {
			throw new WebInvalidDataException(messages);
		} else {
			final String imageType = fileName.substring(fileName
					.lastIndexOf(".") + 1);
			infoDto.setImageType(imageType);
			final QaImageInfoBO infoBo = CONVERTER
					.convertQaImageDTOToBO(infoDto);
			return CONVERTER.convertQaImageBOToDTO(QA_IMG_SER.save(infoBo));
		}
	}

	@Override
	public void delete(final Long imageId) {
		QA_IMG_SER.delete(imageId);
	}

	@Override
	public List<QaImageInfoDTO> findAll() {
		return CONVERTER.convertListQaImageBOToDTO(QA_IMG_SER.findAll());
	}

	@Override
	public List<QaImageInfoDTO> findSelected(final QaImageInfoDTO infoDto) {
		final QaImageInfoBO infoBo = CONVERTER.convertQaImageDTOToBO(infoDto);
		return CONVERTER.convertListQaImageBOToDTO(QA_IMG_SER
				.findSelected(infoBo));
	}

}
