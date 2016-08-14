/**
 * 
 */
package com.qaforum.rest.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.qaforum.dto.QaInfoDTO;

/**
 * @author cdacr
 * 
 */
@XmlRootElement
public final class Message {

	/** */
	private String type;
	/** */
	private String description;
	/** */
	private List<QaInfoDTO> qaInfos = new ArrayList<>();

	/** */
	public Message() {
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the qaInfos
	 */
	public List<QaInfoDTO> getQaInfos() {
		return qaInfos;
	}

	/**
	 * 
	 * @param qaInfos 
	 */
	public void setQaInfos(final List<QaInfoDTO> qaInfos) {
		this.qaInfos = qaInfos;
	}

	/**
	 * 
	 * @param qaInfo 
	 */
	public void addQaInfo(final QaInfoDTO qaInfo) {
		this.qaInfos.add(qaInfo);
	}

	/**
	 * 
	 * @param qaInfos 
	 */
	public void addQaInfos(final List<QaInfoDTO> qaInfos) {
		this.qaInfos.addAll(qaInfos);
	}

	/**
	 * @param type 
	 *            the type to set
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
}
