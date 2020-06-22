package br.com.eubusco.server.bo.impl;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.AddressBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.AddressDAO;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class AddressBOImpl implements AddressBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AddressDAO addressDAO;

	@Override
	public Endereco getAddressFromCode(Integer addressCode) {
		logger.info("==> Executando o método adquirirTodosUsuario.");

		if (addressCode == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return addressDAO.buscarPorId(addressCode);
	}

}
