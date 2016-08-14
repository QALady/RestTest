/**
 * 
 */
package com.qaforum.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cdacr
 * 
 */
public class QaInfoDTO implements Serializable {

	private static final long serialVersionUID = 1677917346380389565L;

	/**
	 * 
	 */
	private Long qaId;
	/** */
	private String question;
	/** */
	private String answer;
	/** */
	private String type;
	/** */
	private Date lastUpdatedDt;
	/** */
	private String lastUpdatedBy;

	/**
	 * @return the qaId
	 */
	public final Long getQaId() {
		return qaId;
	}

	/**
	 * @param qaId
	 *            the qaId to set
	 */
	public final void setQaId(final Long qaId) {
		this.qaId = qaId;
	}

	/**
	 * @return the question
	 */
	public final String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public final void setQuestion(final String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public final String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public final void setAnswer(final String answer) {
		this.answer = answer;
	}

	/**
	 * @return the typeId
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public final void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the lastUpdatedDt
	 */
	public final Date getLastUpdatedDt() {
		return lastUpdatedDt;
	}

	/**
	 * @param lastUpdatedDt
	 *            the lastUpdatedDt to set
	 */
	public final void setLastUpdatedDt(final Date lastUpdatedDt) {
		this.lastUpdatedDt = lastUpdatedDt;
	}

	/**
	 * @return the lastUpdatedBy
	 */
	public final String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * @param lastUpdatedBy
	 *            the lastUpdatedBy to set
	 */
	public final void setLastUpdatedBy(final String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public final String toString() {
		return "Question " + this.qaId + ": " + this.question + " \nAnswer : "
				+ this.answer;
	}

}
