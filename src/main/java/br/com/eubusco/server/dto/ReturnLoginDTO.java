package br.com.eubusco.server.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReturnLoginDTO {

	private Integer userCode;
	private Boolean success;
	private Integer userType;
	private String userName;
	private BigDecimal rating;
	private List<DeliveryDTO> toDeliver;
	private List<DeliveryDTO> deliveriesToUser;
	private List<DeliveryDTO> openDeliveries;

	public ReturnLoginDTO() {
	}

	public ReturnLoginDTO(Integer userCode, Boolean success, Integer userType, String userName, BigDecimal rating,
			List<DeliveryDTO> toDeliver, List<DeliveryDTO> deliveriesToUser, List<DeliveryDTO> openDeliveries) {
		super();
		this.userCode = userCode;
		this.success = success;
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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
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

	public List<DeliveryDTO> getToDeliver() {
		return toDeliver;
	}

	public void setToDeliver(List<DeliveryDTO> toDeliver) {
		this.toDeliver = toDeliver;
	}

	public List<DeliveryDTO> getDeliveriesToUser() {
		return deliveriesToUser;
	}

	public void setDeliveriesToUser(List<DeliveryDTO> deliveriesToUser) {
		this.deliveriesToUser = deliveriesToUser;
	}

	public List<DeliveryDTO> getOpenDeliveries() {
		return openDeliveries;
	}

	public void setOpenDeliveries(List<DeliveryDTO> openDeliveries) {
		this.openDeliveries = openDeliveries;
	}

}
