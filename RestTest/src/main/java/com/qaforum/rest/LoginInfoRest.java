/**
 * 
 */
package com.qaforum.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.qaforum.dataprovider.LoginInfoDataProvider;
import com.qaforum.dataprovider.impl.LoginInfoDataProviderImpl;
import com.qaforum.dto.LoginInfoDTO;
import com.qaforum.dto.LogoutDTO;
import com.qaforum.rest.response.LoginResponse;
import com.qaforum.util.MessageConstants;

/**
 * @author cdacr
 *
 */
@Path("login")
public final class LoginInfoRest {

	/** */
	private final Logger logger = Logger.getLogger(LoginInfoRest.class
			.getName());

	/** */
	@Context
	private HttpServletRequest request;

	/** */
	private static final LoginInfoDataProvider LOGIN_DP = new LoginInfoDataProviderImpl();

	/**
	 * 
	 * @param loginDto 
	 * @return {@link LoginResponse}
	 */
	@POST
	@Path("addUser")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse save(final LoginInfoDTO loginDto) {
		final LoginResponse response = new LoginResponse();
		try {
			LOGIN_DP.save(loginDto);
			response.setType(MessageConstants.SUCCESS);
			response.setDescription(MessageConstants.LOGIN_SUCCESS_MSG);
			response.setLoginDto(loginDto);
		} catch (final Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setType(MessageConstants.ERROR);
			response.setDescription(MessageConstants.LOGIN_ERROR_MSG);
		}
		return response;
	}

	/**
	 * 
	 * @param loginDto 
	 * @return {@link LoginResponse}
	 */
	@PUT
	@Path("updateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse update(final LoginInfoDTO loginDto) {
		final LoginResponse response = new LoginResponse();
		try {
			LOGIN_DP.save(loginDto);
			response.setType(MessageConstants.SUCCESS);
			response.setDescription(MessageConstants.LOGIN_UPD_S_MSG);
			response.setLoginDto(loginDto);
		} catch (final Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setType(MessageConstants.ERROR);
			response.setDescription(MessageConstants.LOGIN_UPD_E_MSG);
		}
		return response;
	}

	/**
	 * 
	 * @param loginId 
	 * @return {@link LoginResponse}
	 */
	@DELETE
	@Path("removeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse delete(final String loginId) {
		final LoginResponse response = new LoginResponse();

		try {
			LOGIN_DP.delete(loginId);
			response.setType(MessageConstants.SUCCESS);
			response.setDescription(MessageConstants.LOGIN_DEL_S_MSG);
		} catch (final Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setType(MessageConstants.ERROR);
			response.setDescription(MessageConstants.LOGIN_DEL_E_MSG);
		}
		return response;
	}

	/**
	 * 
	 * @param loginDto 
	 * @return {@link LoginResponse}
	 */
	@POST
	@Path("authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse authenticateUser(final LoginInfoDTO loginDto) {
		final LoginResponse response = new LoginResponse();

		try {
			if (LOGIN_DP.isAuthenticatedUser(loginDto)) {
				final String sessionId = request.getSession(true).getId();
				final LoginInfoDTO userData = LOGIN_DP.getLoginInfo(loginDto
						.getLoginId());
				userData.setSessionId(sessionId);
				userData.setIsLogin(Boolean.TRUE);

				LOGIN_DP.updateSessionId(userData.getLoginId(), sessionId);

				response.setType(MessageConstants.SUCCESS);
				response.setDescription(MessageConstants.LOGIN_AUTH_S_MSG);
				response.setLoginDto(userData);
				logger.info("Session Id : " + userData.getSessionId());
			} else {
				response.setType(MessageConstants.ERROR);
				response.setDescription(MessageConstants.LOGIN_AUTH_UA_MSG);
			}
		} catch (final Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			response.setType(MessageConstants.ERROR);
			response.setDescription(MessageConstants.LOGIN_AUTH_E_MSG);
		}
		return response;
	}

	/**
	 * 
	 * @param loginId 
	 * @return LoginResponse
	 */
	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse logout(final LogoutDTO logoutDTO) {
		logger.info("LoginId :: " + logoutDTO.getLoginId());
		final LoginResponse response = new LoginResponse();
		try {
			final LoginInfoDTO loginDto = LOGIN_DP.getLoginInfo(logoutDTO
					.getLoginId());
			loginDto.setIsLogin(Boolean.FALSE);
			LOGIN_DP.save(loginDto);
			request.getSession().invalidate();
			response.setType(MessageConstants.SUCCESS);
		} catch (final Exception ex) {
			response.setType(MessageConstants.ERROR);
		}
		return response;
	}
}
