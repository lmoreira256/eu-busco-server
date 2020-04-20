package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.dto.DeliveryDTO;

public interface DeliveryBO {

	public abstract List<DeliveryDTO> getUserDeliveries(Integer userCode);

}
