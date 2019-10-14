package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EstadoBO;
import br.com.eubusco.server.dao.EstadoDAO;
import br.com.eubusco.server.model.Estado;

@ManagedBean
public class EstadoBOImpl implements EstadoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EstadoDAO estadoDAO;

	@Override
	public List<Estado> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return estadoDAO.buscarTodos();
	}

}
