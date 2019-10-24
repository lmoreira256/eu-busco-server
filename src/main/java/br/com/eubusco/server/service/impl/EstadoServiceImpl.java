package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EstadoBO;
import br.com.eubusco.server.model.Estado;
import br.com.eubusco.server.service.EstadoService;

@ManagedBean
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoBO estadoBO;

	@Override
	public List<Estado> adquirirTodos() {
		return estadoBO.adquirirTodos();
	}

}
