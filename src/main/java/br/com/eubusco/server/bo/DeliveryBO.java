package br.com.eubusco.server.bo;

import br.com.eubusco.server.dto.PaginationDTO;

public interface DeliveryBO {

	public abstract PaginationDTO getUserDeliveries(Integer userCode, Integer page);

}
