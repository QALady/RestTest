/**
 * 
 */
package com.qaforum.rest.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.qaforum.dto.LoginInfoDTO;

/**
 * @author cdacr
 *
 */
@XmlRootElement
public final class LoginResponse {
	/** */
	private String type;
	/** */
	private String description;
	/** */
	private LoginInfoDTO loginDto;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the loginDto
	 */
	public LoginInfoDTO getLoginDto() {
		return loginDto;
	}

	/**
	 * @param loginDto the loginDto to set
	 */
	public void setLoginDto(final LoginInfoDTO loginDto) {
		this.loginDto = loginDto;
	}
}
