/**
 * 
 */
package com.qaforum.converter.impl;

import java.util.ArrayList;
import java.util.List;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.bo.QaImageInfoBO;
import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.converter.Converter;
import com.qaforum.dto.LoginInfoDTO;
import com.qaforum.dto.QaImageInfoDTO;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;

/**
 * @author cdacr
 * 
 */
public final class ConverterImpl implements Converter {
	/**
	 * 
	 * @param searchDTO 
	 * @return {@link QaInfoSearchBO}
	 */
	@Override
	public QaInfoSearchBO convertSearchDTOToBO(final QaInfoSearchDTO searchDTO) {
		final QaInfoSearchBO searchBO = new QaInfoSearchBO();
		searchBO.setOperator(searchDTO.getOperator());
		searchBO.setSearchOption(searchDTO.getSearchOption());
		searchBO.setSearchValue(searchDTO.getSearchValue());

		return searchBO;
	}

	/**
	 * 
	 * @param qaDto 
	 * @return {@link QaInfoBO}
	 */
	@Override
	public QaInfoBO convertQaInfoDTOToBO(final QaInfoDTO qaDto) {
		final QaInfoBO qaBo = new QaInfoBO();

		qaBo.setQaId(qaDto.getQaId());
		qaBo.setQuestion(qaDto.getQuestion());
		qaBo.setAnswer(qaDto.getAnswer());
		qaBo.setLastUpdatedBy(qaDto.getLastUpdatedBy());
		qaBo.setLastUpdatedDt(qaDto.getLastUpdatedDt());
		qaBo.setType(qaDto.getType());

		return qaBo;
	}

	/**
	 * 
	 * @param qaBo 
	 * @return {@link QaInfoDTO}
	 */
	@Override
	public QaInfoDTO convertQaInfoBOToDTO(final QaInfoBO qaBo) {
		final QaInfoDTO qaDto = new QaInfoDTO();

		qaDto.setQaId(qaBo.getQaId());
		qaDto.setQuestion(qaBo.getQuestion());
		qaDto.setAnswer(qaBo.getAnswer());
		qaDto.setLastUpdatedBy(qaBo.getLastUpdatedBy());
		qaDto.setLastUpdatedDt(qaBo.getLastUpdatedDt());
		qaDto.setType(qaBo.getType());

		return qaDto;
	}

	/**
	 * 
	 * @param qaDtos 
	 * @return List of {@link QaInfoBO}
	 */
	@Override
	public List<QaInfoBO> convertListQaDTOToBO(final List<QaInfoDTO> qaDtos) {
		final List<QaInfoBO> qaBos = new ArrayList<>();
		for (final QaInfoDTO qaDto : qaDtos) {
			qaBos.add(convertQaInfoDTOToBO(qaDto));
		}
		return qaBos;
	}

	/**
	 * 
	 * @param qaBos 
	 * @return List of {@link QaInfoDTO}
	 */
	@Override
	public List<QaInfoDTO> convertListQaBOToDTO(final List<QaInfoBO> qaBos) {
		final List<QaInfoDTO> qaDtos = new ArrayList<>();
		for (final QaInfoBO qaBo : qaBos) {
			qaDtos.add(convertQaInfoBOToDTO(qaBo));
		}
		return qaDtos;
	}

	@Override
	public QaInfoSearchDTO convertSearchBOToDTO(final QaInfoSearchBO searchBo) {
		final QaInfoSearchDTO searchDto = new QaInfoSearchDTO();
		searchDto.setOperator(searchBo.getOperator());
		searchDto.setSearchOption(searchBo.getSearchOption());
		searchDto.setSearchValue(searchBo.getSearchValue());

		return searchDto;
	}

	@Override
	public LoginInfoDTO convertLoginBOToDTO(final LoginInfoBO loginBo) {
		final LoginInfoDTO loginDto = new LoginInfoDTO();
		loginDto.setInsertedBy(loginBo.getInsertedBy());
		loginDto.setInsertedDt(loginBo.getInsertedDt());
		loginDto.setIsLogin(loginBo.getIsLogin());
		loginDto.setLastLoginDt(loginBo.getLastLoginDt());
		loginDto.setLastLoginIpAddress(loginBo.getLastLoginIpAddress());
		loginDto.setLoginId(loginBo.getLoginId());
		loginDto.setPassword(loginBo.getPassword());
		loginDto.setUpdatedBy(loginBo.getUpdatedBy());
		loginDto.setUpdatedDt(loginBo.getUpdatedDt());
		return loginDto;
	}

	@Override
	public LoginInfoBO convertLoginDTOToBO(final LoginInfoDTO loginDto) {
		final LoginInfoBO loginBo = new LoginInfoBO();
		loginBo.setInsertedBy(loginDto.getInsertedBy());
		loginBo.setInsertedDt(loginDto.getInsertedDt());
		loginBo.setIsLogin(loginDto.getIsLogin());
		loginBo.setLastLoginDt(loginDto.getLastLoginDt());
		loginBo.setLastLoginIpAddress(loginDto.getLastLoginIpAddress());
		loginBo.setLoginId(loginDto.getLoginId());
		loginBo.setPassword(loginDto.getPassword());
		loginBo.setUpdatedBy(loginDto.getUpdatedBy());
		loginBo.setUpdatedDt(loginDto.getUpdatedDt());
		return loginBo;
	}

	@Override
	public QaImageInfoBO convertQaImageDTOToBO(final QaImageInfoDTO infoDto) {
		final QaImageInfoBO infoBo = new QaImageInfoBO();

		infoBo.setImageId(infoDto.getImageId());
		infoBo.setImageType(infoDto.getImageType());
		infoBo.setQaId(infoDto.getQaId());

		return infoBo;
	}

	@Override
	public QaImageInfoDTO convertQaImageBOToDTO(final QaImageInfoBO infoBo) {
		final QaImageInfoDTO infoDto = new QaImageInfoDTO();

		infoDto.setImageId(infoBo.getImageId());
		infoDto.setImageType(infoBo.getImageType());
		infoDto.setQaId(infoBo.getQaId());
		infoDto.setImagePath(infoBo.getImageId() + "_" + infoBo.getQaId() + "."
				+ infoBo.getImageType());
		return infoDto;
	}

	@Override
	public List<QaImageInfoBO> convertListQaImageDTOToBO(
			final List<QaImageInfoDTO> infoDtos) {
		final List<QaImageInfoBO> infoBos = new ArrayList<>();
		for (final QaImageInfoDTO infoDto : infoDtos) {
			final QaImageInfoBO infoBo = new QaImageInfoBO();

			infoBo.setImageId(infoDto.getImageId());
			infoBo.setImageType(infoDto.getImageType());
			infoBo.setQaId(infoDto.getQaId());

			infoBos.add(infoBo);
		}
		return infoBos;
	}

	@Override
	public List<QaImageInfoDTO> convertListQaImageBOToDTO(
			final List<QaImageInfoBO> infoBos) {

		final List<QaImageInfoDTO> infoDtos = new ArrayList<>();

		for (final QaImageInfoBO infoBo : infoBos) {
			final QaImageInfoDTO infoDto = new QaImageInfoDTO();

			infoDto.setImageId(infoBo.getImageId());
			infoDto.setImageType(infoBo.getImageType());
			infoDto.setQaId(infoBo.getQaId());

			infoDto.setImagePath(infoBo.getImageId() + "_" + infoBo.getQaId()
					+ "." + infoBo.getImageType());

			infoDtos.add(infoDto);
		}

		return infoDtos;
	}
}
