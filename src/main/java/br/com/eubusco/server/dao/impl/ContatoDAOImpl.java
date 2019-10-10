package br.com.eubusco.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.model.Contato;

@Repository
public class ContatoDAOImpl extends GenericDAOImpl<Contato> implements ContatoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

}
