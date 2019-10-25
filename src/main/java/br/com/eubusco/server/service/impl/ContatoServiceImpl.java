package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.ContatoBO;
import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.service.ContatoService;

@ManagedBean
public class ContatoServiceImpl implements ContatoService {

	@Autowired
	private ContatoBO contatoBO;

	@Override
	public List<Contato> adquirirPorUsuario(Integer codigoUsuario) {
		return contatoBO.adquirirPorUsuario(codigoUsuario);
	}

	@Override
	public Boolean salvarContato(@RequestBody @Valid Contato contato) {
		return contatoBO.salvarContato(contato);
	}

}
