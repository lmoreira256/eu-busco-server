package br.com.eubusco.server.service.impl;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.AvaliacaoBO;
import br.com.eubusco.server.model.Avaliacao;
import br.com.eubusco.server.service.AvaliacaoService;

@ManagedBean
public class AvaliacaoServiceImpl implements AvaliacaoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AvaliacaoBO avaliacaoBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Avaliacao avaliacao) {
		logger.info("==> Executando o método salvar.");

		return avaliacaoBO.salvar(avaliacao);
	}

}
