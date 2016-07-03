/**
 * 
 */
package com.qaforum.dataprovider;

import com.qaforum.converter.Converter;
import com.qaforum.converter.impl.ConverterImpl;
import com.qaforum.dto.LoginInfoDTO;
import com.qaforum.service.LoginInfoService;
import com.qaforum.service.impl.LoginInfoServiceImpl;

/**
 * @author cdacr
 *
 */
public interface LoginInfoDataProvider {
	/** */
	LoginInfoService LOGIN_SER = new LoginInfoServiceImpl();
	/** */
	Converter CONVERTER = new ConverterImpl();

	/**
	 * 
	 * @param loginDto 
	 * @return {@link LoginInfoDTO}
	 */
	LoginInfoDTO save(LoginInfoDTO loginDto);

	/**
	 * 
	 * @param loginId 
	 */
	void delete(String loginId);

	/**
	 * 
	 * @param loginId 
	 * @return {@link LoginInfoDTO}
	 */
	LoginInfoDTO getLoginInfo(String loginId);

	/**
	 * 
	 * @param loginDto 
	 * @return TRUE|FALSE
	 */
	boolean isAuthenticatedUser(LoginInfoDTO loginDto);

	/**
	 * 
	 * @param loginId 
	 * @param sessionId 
	 */
	void updateSessionId(String loginId, String sessionId);
}
