package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.TipoUsuarioBO;
import br.com.eubusco.server.model.TipoUsuario;
import br.com.eubusco.server.service.TipoUsuarioService;

@ManagedBean
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TipoUsuarioBO tipoUsuarioBO;

	@Override
	public List<TipoUsuario> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return tipoUsuarioBO.adquirirTodos();
	}

}
