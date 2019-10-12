package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.TipoUsuarioBO;
import br.com.eubusco.server.dao.TipoUsuarioDAO;
import br.com.eubusco.server.model.TipoUsuario;

@ManagedBean
public class TipoUsuarioBOImpl implements TipoUsuarioBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TipoUsuarioDAO tipoUsuarioDAO;

	@Override
	public List<TipoUsuario> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return tipoUsuarioDAO.buscarTodos();
	}

}
