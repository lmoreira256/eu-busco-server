package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.TipoContatoBO;
import br.com.eubusco.server.model.TipoContato;
import br.com.eubusco.server.service.TipoContatoService;

@ManagedBean
public class TipoContatoServiceImpl implements TipoContatoService {

	@Autowired
	private TipoContatoBO tipoContatoBO;

	@Override
	public List<TipoContato> adquirirTodos() {
		return tipoContatoBO.adquirirTodos();
	}

}
