package br.com.eubusco.server.bo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.DeliveryBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.DeliveryDAO;
import br.com.eubusco.server.dto.DeliveryDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class DeliveryBOImpl implements DeliveryBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeliveryDAO deliveryDAO;

	@Override
	public List<DeliveryDTO> fetchUserDeliveries(Integer userCode) {
		logger.info("==> Executando o método fetchUserDeliveries.");

		if (userCode == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		List<Entrega> entregaList = deliveryDAO.fetchUserDeliveries(userCode);

		List<DeliveryDTO> retorno = new ArrayList<>();

		return retorno;
	}

}
