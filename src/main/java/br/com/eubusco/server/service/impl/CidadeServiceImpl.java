package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

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

	@Override
	public Boolean salvar(@RequestBody @Valid Cidade cidade) {
		logger.info("==> Executando o método salvar.");

		return cidadeBO.salvar(cidade);
	}

}
