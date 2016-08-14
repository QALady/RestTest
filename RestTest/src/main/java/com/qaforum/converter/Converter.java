/**
 * 
 */
package com.qaforum.converter;

import java.util.List;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.bo.QaImageInfoBO;
import com.qaforum.bo.QaInfoBO;
import com.qaforum.bo.QaInfoSearchBO;
import com.qaforum.dto.LoginInfoDTO;
import com.qaforum.dto.QaImageInfoDTO;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;

/**
 * @author cdacr
 *
 */
public interface Converter {
	/**
	 * 
	 * @param searchDTO 
	 * @return {@link QaInfoSearchBO}
	 */
	QaInfoSearchBO convertSearchDTOToBO(final QaInfoSearchDTO searchDTO);

	/**
	 * 
	 * @param searchBo 
	 * @return {@link QaInfoSearchDTO}
	 */
	QaInfoSearchDTO convertSearchBOToDTO(final QaInfoSearchBO searchBo);

	/**
	 * 
	 * @param qaDto 
	 * @return {@link QaInfoBO}
	 */
	QaInfoBO convertQaInfoDTOToBO(final QaInfoDTO qaDto);

	/**
	 * 
	 * @param qaBo 
	 * @return {@link QaInfoDTO}
	 */
	QaInfoDTO convertQaInfoBOToDTO(final QaInfoBO qaBo);

	/**
	 * 
	 * @param qaDtos 
	 * @return List of {@link QaInfoBO}
	 */
	List<QaInfoBO> convertListQaDTOToBO(final List<QaInfoDTO> qaDtos);

	/**
	 * 
	 * @param qaBos 
	 * @return List of {@link QaInfoDTO}
	 */
	List<QaInfoDTO> convertListQaBOToDTO(final List<QaInfoBO> qaBos);

	/**
	 * 
	 * @param loginBo 
	 * @return {@link LoginInfoDTO}
	 */
	LoginInfoDTO convertLoginBOToDTO(final LoginInfoBO loginBo);

	/**
	 * 
	 * @param loginDto 
	 * @return {@link LoginInfoBO}
	 */
	LoginInfoBO convertLoginDTOToBO(final LoginInfoDTO loginDto);

	/**
	 * 
	 * @param infoDto 
	 * @return {@link QaImageInfoBO}
	 */
	QaImageInfoBO convertQaImageDTOToBO(final QaImageInfoDTO infoDto);

	/**
	 * 
	 * @param infoBo 
	 * @return {@link QaImageInfoDTO}
	 */
	QaImageInfoDTO convertQaImageBOToDTO(final QaImageInfoBO infoBo);

	/**
	 * 
	 * @param infoDtos 
	 * @return List of {@link QaImageInfoBO}
	 */
	List<QaImageInfoBO> convertListQaImageDTOToBO(
			final List<QaImageInfoDTO> infoDtos);

	/**
	 * 
	 * @param infoBos 
	 * @return List of {@link QaImageInfoDTO}
	 */
	List<QaImageInfoDTO> convertListQaImageBOToDTO(
			final List<QaImageInfoBO> infoBos);
}
