package br.com.eubusco.server.dto;

import java.util.Date;
import java.util.List;

import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.model.Endereco;

public class DeliveryDTO {

	private Integer deliveryCode;
	private String clientName;
	private Endereco deliveryAddress;
	private Endereco collectionAddress;
	private String deliveryTitle;
	private String deliveryDescriptio;
	private Date collectionDate;
	private Date deliveryDate;
	private String bulk;
	private List<Contato> contacts;

	public DeliveryDTO() {
	}

	public DeliveryDTO(Integer deliveryCode, String clientName, Endereco deliveryAddress, Endereco collectionAddress,
			String deliveryTitle, String deliveryDescriptio, Date collectionDate, Date deliveryDate, String bulk,
			List<Contato> contacts) {
		super();
		this.deliveryCode = deliveryCode;
		this.clientName = clientName;
		this.deliveryAddress = deliveryAddress;
		this.collectionAddress = collectionAddress;
		this.deliveryTitle = deliveryTitle;
		this.deliveryDescriptio = deliveryDescriptio;
		this.collectionDate = collectionDate;
		this.deliveryDate = deliveryDate;
		this.bulk = bulk;
		this.contacts = contacts;
	}

	public String getBulk() {
		return bulk;
	}

	public Endereco getDeliveryAddress() {
		return deliveryAddress;
	}

	public String getClientName() {
		return clientName;
	}

	public Endereco getCollectionAddress() {
		return collectionAddress;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public List<Contato> getContacts() {
		return contacts;
	}

	public Integer getDeliveryCode() {
		return deliveryCode;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String getDeliveryDescriptio() {
		return deliveryDescriptio;
	}

	public String getDeliveryTitle() {
		return deliveryTitle;
	}

	public void setBulk(String bulk) {
		this.bulk = bulk;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setCollectionAddress(Endereco collectionAddress) {
		this.collectionAddress = collectionAddress;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public void setContacts(List<Contato> contacts) {
		this.contacts = contacts;
	}

	public void setDeliveryAddress(Endereco deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setDeliveryCode(Integer deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setDeliveryDescriptio(String deliveryDescriptio) {
		this.deliveryDescriptio = deliveryDescriptio;
	}

	public void setDeliveryTitle(String deliveryTitle) {
		this.deliveryTitle = deliveryTitle;
	}

}
