/**
 * 
 */
package com.qaforum.dataprovider.impl;

import java.util.Date;

import com.qaforum.bo.LoginInfoBO;
import com.qaforum.dataprovider.LoginInfoDataProvider;
import com.qaforum.dto.LoginInfoDTO;

/**
 * @author cdacr
 *
 */
public final class LoginInfoDataProviderImpl implements LoginInfoDataProvider {

	@Override
	public LoginInfoDTO save(final LoginInfoDTO loginDto) {
		loginDto.setInsertedDt(new Date());
		loginDto.setUpdatedDt(new Date());
		final LoginInfoBO loginBo = CONVERTER.convertLoginDTOToBO(loginDto);
		return CONVERTER.convertLoginBOToDTO(LOGIN_SER.save(loginBo));
	}

	@Override
	public void delete(final String loginId) {
		LOGIN_SER.delete(loginId);
	}

	@Override
	public LoginInfoDTO getLoginInfo(final String loginId) {
		return CONVERTER.convertLoginBOToDTO(LOGIN_SER.getLoginInfo(loginId));
	}

	@Override
	public boolean isAuthenticatedUser(final LoginInfoDTO loginDto) {
		final LoginInfoBO loginBo = CONVERTER.convertLoginDTOToBO(loginDto);
		return LOGIN_SER.isAuthenticatedUser(loginBo);
	}

	@Override
	public void updateSessionId(final String loginId, final String sessionId) {
		LOGIN_SER.updateSessionId(loginId, sessionId);
	}
}
