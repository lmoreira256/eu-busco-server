package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.CidadeBO;
import br.com.eubusco.server.dao.CidadeDAO;
import br.com.eubusco.server.model.Cidade;

@ManagedBean
public class CidadeBOImpl implements CidadeBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CidadeDAO cidadeDAO;

	@Override
	public List<Cidade> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return cidadeDAO.buscarTodos();
	}

}
