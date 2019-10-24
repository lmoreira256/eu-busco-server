package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.TipoUsuarioBO;
import br.com.eubusco.server.model.TipoUsuario;
import br.com.eubusco.server.service.TipoUsuarioService;

@ManagedBean
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

	@Autowired
	private TipoUsuarioBO tipoUsuarioBO;

	@Override
	public List<TipoUsuario> adquirirTodos() {
		return tipoUsuarioBO.adquirirTodos();
	}

}
