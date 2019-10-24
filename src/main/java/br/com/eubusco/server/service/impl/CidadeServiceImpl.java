package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.CidadeBO;
import br.com.eubusco.server.model.Cidade;
import br.com.eubusco.server.service.CidadeService;

@ManagedBean
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeBO cidadeBO;

	@Override
	public List<Cidade> adquirirTodos() {
		return cidadeBO.adquirirTodos();
	}

	@Override
	public Boolean salvar(@RequestBody @Valid Cidade cidade) {
		return cidadeBO.salvar(cidade);
	}

}
