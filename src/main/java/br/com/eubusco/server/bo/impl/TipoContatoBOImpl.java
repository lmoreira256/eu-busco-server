package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.TipoContatoBO;
import br.com.eubusco.server.dao.TipoContatoDAO;
import br.com.eubusco.server.model.TipoContato;

@ManagedBean
public class TipoContatoBOImpl implements TipoContatoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TipoContatoDAO tipoContatoDAO;

	@Override
	public List<TipoContato> adquirirTodos() {
		logger.info("==> Executando o método adquirirTodos.");

		return tipoContatoDAO.buscarTodos();
	}

}
