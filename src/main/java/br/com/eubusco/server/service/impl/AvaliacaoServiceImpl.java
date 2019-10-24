package br.com.eubusco.server.service.impl;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.AvaliacaoBO;
import br.com.eubusco.server.model.Avaliacao;
import br.com.eubusco.server.service.AvaliacaoService;

@ManagedBean
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoBO avaliacaoBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Avaliacao avaliacao) {
		return avaliacaoBO.salvar(avaliacao);
	}

}
