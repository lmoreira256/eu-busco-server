package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.CidadeBO;
import br.com.eubusco.server.model.Cidade;
import br.com.eubusco.server.service.CidadeService;

@ManagedBean
public class CidadeServiceImpl implements CidadeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CidadeBO cidadeBO;

	@Override
	public List<Cidade> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return cidadeBO.adquirirTodos();
	}

}
