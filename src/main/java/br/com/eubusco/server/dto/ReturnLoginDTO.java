package br.com.eubusco.server.dto;

import java.math.BigDecimal;

public class ReturnLoginDTO {

	private Integer userCode;
	private Integer userType;
	private String userName;
	private BigDecimal rating;
	private PaginationDTO toDeliver;
	private PaginationDTO deliveriesToUser;
	private PaginationDTO openDeliveries;

	public ReturnLoginDTO() {
	}

	public ReturnLoginDTO(Integer userCode, Boolean success, Integer userType, String userName, BigDecimal rating,
			PaginationDTO toDeliver, PaginationDTO deliveriesToUser, PaginationDTO openDeliveries) {
		super();
		this.userCode = userCode;
		this.userType = userType;
		this.userName = userName;
		this.rating = rating;
		this.toDeliver = toDeliver;
		this.deliveriesToUser = deliveriesToUser;
		this.openDeliveries = openDeliveries;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public PaginationDTO getToDeliver() {
		return toDeliver;
	}

	public void setToDeliver(PaginationDTO toDeliver) {
		this.toDeliver = toDeliver;
	}

	public PaginationDTO getDeliveriesToUser() {
		return deliveriesToUser;
	}

	public void setDeliveriesToUser(PaginationDTO deliveriesToUser) {
		this.deliveriesToUser = deliveriesToUser;
	}

	public PaginationDTO getOpenDeliveries() {
		return openDeliveries;
	}

	public void setOpenDeliveries(PaginationDTO openDeliveries) {
		this.openDeliveries = openDeliveries;
	}

}
