/**
 * 
 */
package com.qaforum.dto;

/**
 * @author cdacr
 * 
 */
public final class QaInfoSearchDTO {
	/** */
	private String searchOption;
	/** */
	private String operator;
	/** */
	private String searchValue;

	/**
	 * @return the searchOption
	 */
	public String getSearchOption() {
		return searchOption;
	}

	/**
	 * @param searchOption
	 *            the searchOption to set
	 */
	public void setSearchOption(final String searchOption) {
		this.searchOption = searchOption;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(final String operator) {
		this.operator = operator;
	}

	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(final String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Search option : " + searchOption + " Operator: " + operator
				+ " Value: " + searchValue;
	}
}
