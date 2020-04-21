package br.com.eubusco.server.service.impl;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.DeliveryBO;
import br.com.eubusco.server.dto.PaginationDTO;
import br.com.eubusco.server.service.DeliveryService;

@ManagedBean
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryBO deliveryBO;

	@Override
	public PaginationDTO getUserDeliveries(Integer userCode, Integer page) {
		return deliveryBO.getUserDeliveries(userCode, page);
	}

}
