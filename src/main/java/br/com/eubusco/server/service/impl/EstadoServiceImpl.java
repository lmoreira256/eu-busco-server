package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EstadoBO;
import br.com.eubusco.server.model.Estado;
import br.com.eubusco.server.service.EstadoService;

@ManagedBean
public class EstadoServiceImpl implements EstadoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EstadoBO estadoBO;

	@Override
	public List<Estado> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return estadoBO.adquirirTodos();
	}

}
