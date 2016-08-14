/**
 * 
 */
package com.qaforum.dto;

import java.io.Serializable;

/**
 * @author cdacr
 *
 */
public class QaImageInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5526858876857865841L;
	/** */
	private Long imageId;
	/** */
	private Long qaId;
	/**	 */
	private String imageType;
	/** */
	private String imagePath;

	/**
	 * @return the imageId
	 */
	public final Long getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public final void setImageId(final Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the qaId
	 */
	public final Long getQaId() {
		return qaId;
	}

	/**
	 * @param qaId the qaId to set
	 */
	public final void setQaId(final Long qaId) {
		this.qaId = qaId;
	}

	/**
	 * @return the imagePath
	 */
	public final String getImageType() {
		return imageType;
	}

	/**
	 * @param imageType the imageType to set
	 */
	public final void setImageType(final String imageType) {
		this.imageType = imageType;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return qaId + ":" + imageType + ":" + imagePath;
	}
}
