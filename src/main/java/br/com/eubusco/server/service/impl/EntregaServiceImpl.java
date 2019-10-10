package br.com.eubusco.server.service.impl;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.service.EntregaService;

@ManagedBean
public class EntregaServiceImpl implements EntregaService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntregaBO entregaBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Entrega entrega) {
		logger.info("==> Executando o método salvar.");

		return entregaBO.salvar(entrega);
	}

}
