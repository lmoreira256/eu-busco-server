package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.ContactBO;
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.model.Contato;

@ManagedBean
public class ContactBOImpl implements ContactBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContatoDAO contatoDAO;

	@Override
	public List<Contato> getFromUser(Integer userCode) {
		logger.info("==> Executando o método getFromUser.");

		return contatoDAO.adquirirPorUsuario(userCode);
	}

}
