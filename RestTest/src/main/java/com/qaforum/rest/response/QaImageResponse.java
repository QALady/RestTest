/**
 * 
 */
package com.qaforum.rest.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.qaforum.dto.QaImageInfoDTO;

/**
 * @author cdacr
 *
 */
@XmlRootElement
public final class QaImageResponse {

	/** */
	private String type;
	/** */
	private String description;
	/** */
	private List<QaImageInfoDTO> infoDtos = new ArrayList<>();

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the infoDtos
	 */
	public final List<QaImageInfoDTO> getInfoDtos() {
		return infoDtos;
	}

	public void setInfoDtos(final List<QaImageInfoDTO> infoDtos) {
		this.infoDtos = infoDtos;
	}

	public void addInfos(final List<QaImageInfoDTO> infoDtos) {
		this.infoDtos.addAll(infoDtos);
	}

	public void addInfo(final QaImageInfoDTO infoDto) {
		this.infoDtos.add(infoDto);
	}
}
